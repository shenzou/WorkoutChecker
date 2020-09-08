package com.shenzou.workoutchecker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.android.synthetic.main.fragment_seances.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val seancesList: List<Seance>? = null
        csvReader().open("Seances.csv"){
            readAllAsSequence().forEach { row ->

            }
        }*/
        adapter = SeancesAdapter(seancesArray, view.context)
        val dbHandler = DBManager(view.context, null)

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
                    dbHandler.addSeance(seance)
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
        val listView = view.findViewById<ListView>(R.id.listview_seances)
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

            do {
                val name = cursor.getString(seanceTitle)
                val date = cursor.getString(seanceDate)
                val tempSeance: Seance = Seance(parser.parse(date), name)
                seancesArray.add(tempSeance)
            }
            while (cursor.moveToNext())
            cursor.close()

        }
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