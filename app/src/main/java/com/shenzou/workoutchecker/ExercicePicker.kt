package com.shenzou.workoutchecker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExercicePicker : AppCompatActivity() {

    private lateinit var adapter: ExercicesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercice_picker)

        val poids = intent.getStringExtra("poids")
        val reps = intent.getStringExtra("reps")

        adapter = ExercicesAdapter(MainActivity.exercicesList, this)
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        adapter.onItemClick = {exercice ->
            val intent = Intent()
            intent.putExtra("exercice", exercice)
            intent.putExtra("poids", poids)
            intent.putExtra("reps", reps)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}