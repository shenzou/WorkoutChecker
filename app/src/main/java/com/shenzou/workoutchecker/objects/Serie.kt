package com.shenzou.workoutchecker.objects

import java.io.Serializable

class Serie(var exercice: Exercice, var reps: Int): Serializable {
    var poids : Double = 0.0
}