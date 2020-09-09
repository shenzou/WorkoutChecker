package com.shenzou.workoutchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import java.io.Serializable
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
    }
}