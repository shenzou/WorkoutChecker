package com.shenzou.workoutchecker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_seances.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_seances.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_seances : Fragment() {

    private lateinit var viewOfLayout: View
    val seancesArray: ArrayList<Seance> = ArrayList()
    private lateinit var adapter: SeancesAdapter

    companion object{
        fun newInstance(): fragment_seances{
            return fragment_seances()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater.inflate(R.layout.fragment_seances, container, false)
        return viewOfLayout
    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context != null){
            val resources = context.resources

        }
    }*/

    override fun onResume() {
        super.onResume()
        context?.let { populateArraySeances(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val seancesList: List<Seance>? = null
        csvReader().open("Seances.csv"){
            readAllAsSequence().forEach { row ->

            }
        }*/
        adapter = SeancesAdapter(seancesArray, view.context)

        button_add?.setOnClickListener() {
            val dialogView = LayoutInflater.from(it.context).inflate(R.layout.alert_new_seance, null)
            val dateSelect = dialogView.findViewById<TextView>(R.id.editTextDate)
            dateSelect.text = SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis())
            val nameText:EditText = dialogView.findViewById(R.id.seanceName)

            val calendarButton = dialogView.findViewById<ImageButton>(R.id.calendarButton)
            calendarButton.setOnClickListener(){
                val c = Calendar.getInstance()
                val y = c.get(Calendar.YEAR)
                val m = c.get(Calendar.MONTH)
                val d = c.get(Calendar.DAY_OF_MONTH)
                val dp:DatePickerDialog =
                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    DatePickerDialog(it.context, R.style.MySpinnerDatePickerStyle, DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->
                        dateSelect.setText(""+dayOfMonth+ "/" + monthOfYear + "/" + year)
                    }, y, m, d)
                    } else{
                        DatePickerDialog(it.context, DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->
                            dateSelect.setText(""+dayOfMonth+ "/" + monthOfYear + "/" + year)
                        }, y, m, d)
                    }

                dp.show()
            }
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Nouvelle séance")
            builder.setView(dialogView)
            val alertDialog:AlertDialog = builder.create()
            alertDialog.show()

            val valider:Button = dialogView.findViewById(R.id.valider)
            valider.setOnClickListener(){
                if(validateData(nameText.text.toString())){
                    val parser = SimpleDateFormat("dd/MM/yyyy")
                    val date: Date = parser.parse(dateSelect.text.toString())
                    /*if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", null)
                        val date = LocalDate.parse(dateSelect.text, formatter)
                    }*/
                    val seance = Seance(date, nameText.text.toString())
                    val dbHandler = DBManager(view.context, null)
                    dbHandler.addSeance(seance)
                    dbHandler.close()
                    populateArraySeances(it.context)
                    alertDialog.dismiss()
                } else{
                    Toast.makeText(it.context, "Veuillez saisir le nom de la séance", Toast.LENGTH_SHORT).show()
                }
            }
            val annuler:Button = dialogView.findViewById(R.id.annuler)
            annuler.setOnClickListener(){
                alertDialog.cancel()
            }
        }

        populateArraySeances(view.context)
        val listView = view.findViewById<ListView>(R.id.listview_series)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val element: Seance = adapterView.getItemAtPosition(i) as Seance
            val intent = Intent(this.context, SeanceActivity::class.java)
            intent.putExtra("seance", element)
            startActivity(intent)
        }
    }

    fun populateArraySeances(context: Context){
        val dbHandler = DBManager(context, null)
        val cursor: Cursor? = dbHandler.getAllSeances()
        val parser = SimpleDateFormat("dd/MM/yyyy")

        if(cursor != null && cursor.moveToFirst()){
            seancesArray.clear()
            val seanceTitle = cursor.getColumnIndex("name")
            val seanceDate = cursor.getColumnIndex("date")
            val seanceSeries = cursor.getColumnIndex("series")

            Log.d("in populate", "ok")
            do {
                Log.d("in do", "ok")
                val name = cursor.getString(seanceTitle)
                val date = cursor.getString(seanceDate)
                val series = cursor.getString(seanceSeries)
                Log.d("string series", series)

                val tempSeance = Seance(parser.parse(date), name)

                if(series.length > 11){
                    val listSeries = series.split(
                        delimiters = *arrayOf("!"),
                        ignoreCase = false,
                        limit = 0
                    )
                    Log.d("Length", listSeries.size.toString())


                    for(serie in listSeries){
                        Log.d("Serie", serie)
                        if(serie.length>11){
                            val champs = serie.split(
                                delimiters = *arrayOf(";"),
                                ignoreCase = false,
                                limit = 0
                            )
                            for(champ in champs){
                                if(champ.isNotEmpty()){
                                    Log.d("Champ", champ)
                                } else{
                                    Log.d("Champ", "vide")
                                }

                            }

                            val muscles: ArrayList<Muscle> = ArrayList<Muscle>()

                            val musclesNoRes = champs[3].split(
                                delimiters = *arrayOf(","),
                                ignoreCase = false,
                                limit = 0
                            )
                            for(muscle in musclesNoRes){
                                for(muscleComplet in MainActivity.musclesList){
                                    if(muscle.equals(muscleComplet.name)){
                                        muscles.add(muscleComplet)
                                    }
                                }
                            }

                            val musclesSecond: ArrayList<Muscle> = ArrayList<Muscle>()

                            val musclesSecondNoRes = champs[4].split(
                                delimiters = *arrayOf(","),
                                ignoreCase = false,
                                limit = 0
                            )
                            for(muscle in musclesSecondNoRes){
                                for(muscleComplet in MainActivity.musclesList){
                                    if(muscle.equals(muscleComplet.name)){
                                        musclesSecond.add(muscleComplet)
                                    }
                                }
                            }

                            val exercice = Exercice(champs[0], champs[1], muscles, musclesSecond)
                            exercice.videoLink = champs[2]


                            try{
                                val newSerie = Serie(exercice, champs[5].toInt())
                                Log.d("serie exercice n", newSerie.exercice.name)
                                Log.d("serie reps", newSerie.reps.toString())
                                if(champs[6].length > 1){
                                    val poids = champs[6]
                                    newSerie.poids = poids.toDouble()
                                }
                                Log.d("serie poids", newSerie.poids.toString())
                                tempSeance.AddSerie(newSerie)
                            } catch(e: Exception){
                                Log.d("Exception", e.toString())
                            }
                        }


                    }
                }

                seancesArray.add(tempSeance)
            }
            while (cursor.moveToNext())
            cursor.close()

        }
        dbHandler.close()
        adapter.notifyDataSetChanged()
    }

    private fun validateData(name: String):Boolean{
        var valid:Boolean = true
        if(name.equals("")){
            valid = false
        }
        return valid
    }




}