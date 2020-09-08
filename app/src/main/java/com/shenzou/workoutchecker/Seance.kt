package com.shenzou.workoutchecker

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Seance(val date: Date, val name: String) : Serializable {
    val dateStr: String
        get() = SimpleDateFormat("dd/MM/yyyy").format(date)


}