package com.shenzou.workoutchecker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import java.io.Serializable
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class SeanceActivity : AppCompatActivity(), Serializable {

    private lateinit var adapter: SeriesAdapter
    var seance: Seance? = null
    private val EXERCICE_RESULT_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seance)

        seance = intent.getSerializableExtra("seance") as? Seance
        title = seance!!.name


        /*for(exo in MainActivity.exercicesList){
            Log.d("Exercice", exo.name)
            for(muscle in exo.muscles){
                Log.d("Muscles", muscle.name)
                Log.d("Image", muscle.imageRes.toString())
            }
        }*/
        Log.d("series", seance!!.listSeries.size.toString())

        adapter = SeriesAdapter(seance!!.listSeries, this)
        val listView = findViewById<ListView>(R.id.listview_series)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val element: Serie? = adapter.getItem(i)
            val dialogView = LayoutInflater.from(view.context).inflate(R.layout.alert_new_serie, null)
            val reps = dialogView.findViewById<EditText>(R.id.reps)
            val poids = dialogView.findViewById<EditText>(R.id.poids)
            val annuler = dialogView.findViewById<Button>(R.id.cancelButton)
            val valider = dialogView.findViewById<Button>(R.id.validateButton)
            val delete = dialogView.findViewById<Button>(R.id.delete)

            delete.visibility = View.VISIBLE
            valider.text = "Modifier"
            if (element != null) {
                reps.text.append(element.reps.toString())
            }
            if (element != null) {
                poids.text.append(element.poids.toString())
            }

            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("Editer série")
            builder.setView(dialogView)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            valider.setOnClickListener{
                seance!!.listSeries.elementAt(i).poids = poids.text.toString().toDouble()
                seance!!.listSeries.elementAt(i).reps = reps.text.toString().toInt()
                adapter.notifyDataSetChanged()
                writeToDb(seance!!)
                alertDialog.dismiss()
            }

            annuler.setOnClickListener{
                alertDialog.cancel()
            }

            delete.setOnClickListener{
                seance!!.listSeries.removeAt(i)
                adapter.notifyDataSetChanged()
                writeToDb(seance!!)
                alertDialog.dismiss()
            }
        }

        val addButton = findViewById<Button>(R.id.button_add)
        addButton.setOnClickListener(){
            val dialogView = LayoutInflater.from(it.context).inflate(R.layout.alert_new_serie, null)
            val reps = dialogView.findViewById<EditText>(R.id.reps)
            val poids = dialogView.findViewById<EditText>(R.id.poids)
            val annuler = dialogView.findViewById<Button>(R.id.cancelButton)
            val valider = dialogView.findViewById<Button>(R.id.validateButton)

            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Nouvelle série")
            builder.setView(dialogView)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            valider.setOnClickListener(){
                val intent = Intent(it.context, ExercicePicker::class.java)
                intent.putExtra("poids", poids.text.toString())
                intent.putExtra("reps", reps.text.toString())
                startActivityForResult(intent, EXERCICE_RESULT_CODE)
                alertDialog.dismiss()
            }

            annuler.setOnClickListener(){
                alertDialog.cancel()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == EXERCICE_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                val returnExercice: Exercice = data!!.getSerializableExtra("exercice") as Exercice
                val returnPoids: String? = data.getStringExtra("poids")
                val returnReps: String? = data.getStringExtra("reps")

                var poidsDouble: Double
                try{
                    poidsDouble = returnPoids?.toDouble() ?: 0.0
                } catch (e: Exception){
                    poidsDouble = 0.0
                }
                var repsInt: Int
                try{
                    repsInt = returnReps?.toInt() ?: 0
                } catch (e: Exception){
                    repsInt = 0
                }

                val serie = Serie(returnExercice, repsInt)
                serie.poids = poidsDouble

                seance!!.listSeries.add(serie)
                Log.d("series + add", seance!!.listSeries.size.toString())

                writeToDb(seance!!)
                adapter.notifyDataSetChanged()

                // Get String data from Intent
                //val returnString = data!!.getStringExtra("keyName")

                // Set text view with string
                //val textView = findViewById(R.id.textView) as TextView
                //textView.text = returnString
            }
        }
    }

    fun writeToDb(seance: Seance){
        val dbHandler = DBManager(this, null)
        dbHandler.modifySeance(seance)
        dbHandler.close()
    }
}