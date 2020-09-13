package com.shenzou.workoutchecker.objects

import com.shenzou.workoutchecker.R
import java.io.Serializable

class Muscle(name: String, imageRes: Int, imageResSecondary: Int): Serializable {
    var name = ""
    var imageRes = R.drawable.muscle_full
    var imageResSecondary = R.drawable.muscle_full
    init {
        this.name = name
        this.imageRes = imageRes
        this.imageResSecondary = imageResSecondary
    }
}