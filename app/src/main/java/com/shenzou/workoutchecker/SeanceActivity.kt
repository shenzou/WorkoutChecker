package com.shenzou.workoutchecker

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import java.io.Serializable
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class SeanceActivity : AppCompatActivity(), Serializable {

    private lateinit var adapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seance)

        val seance = intent.getSerializableExtra("seance") as? Seance
        title = seance!!.name


        /*for(exo in MainActivity.exercicesList){
            Log.d("Exercice", exo.name)
            for(muscle in exo.muscles){
                Log.d("Muscles", muscle.name)
                Log.d("Image", muscle.imageRes.toString())
            }
        }*/
        val series: ArrayList<Serie> = seance.listSeries

        adapter = SeriesAdapter(series, this)
        val listView = findViewById<ListView>(R.id.listview_series)
        listView.adapter = adapter

        val addButton = findViewById<Button>(R.id.button_add)
        addButton.setOnClickListener(){
            val dialogView = LayoutInflater.from(it.context).inflate(R.layout.alert_new_serie, null)
            val exerciceChooser = dialogView.findViewById<Button>(R.id.chooseExercice)
            val reps = dialogView.findViewById<EditText>(R.id.reps)
            val poids = dialogView.findViewById<EditText>(R.id.poids)
            val annuler = dialogView.findViewById<Button>(R.id.cancelButton)
            val valider = dialogView.findViewById<Button>(R.id.validateButton)

            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Nouvelle série")
            builder.setView(dialogView)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            exerciceChooser.setOnClickListener(){
                val intent = Intent(it.context, ExercicePicker::class.java)
                startActivity(intent)
            }

            valider.setOnClickListener(){

                alertDialog.dismiss()
            }

            annuler.setOnClickListener(){
                alertDialog.cancel()
            }
        }
    }
}