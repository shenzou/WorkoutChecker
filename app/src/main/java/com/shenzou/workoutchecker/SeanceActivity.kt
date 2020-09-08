package com.shenzou.workoutchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.Serializable

class SeanceActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seance)
        val seance = intent.getSerializableExtra("seance") as? Seance
        val name = findViewById<TextView>(R.id.name)
        name.text = seance!!.name
    }
}