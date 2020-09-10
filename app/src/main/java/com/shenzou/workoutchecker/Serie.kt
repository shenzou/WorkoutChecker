package com.shenzou.workoutchecker

import java.io.Serializable

class Serie(exercice: Exercice, reps: Int): Serializable {
    var exercice : Exercice = exercice
    var reps : Int = reps
    var poids : Double = 0.0
}