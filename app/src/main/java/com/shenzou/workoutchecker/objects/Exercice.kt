package com.shenzou.workoutchecker.objects

import java.io.Serializable

class Exercice(name: String, description: String, muscles: List<Muscle>, musclesSecond: List<Muscle>): Serializable {
    var name = ""
    var description = ""
    var videoLink = ""
    var icon = ""
    var muscles : List<Muscle>
    var musclesSecond : List<Muscle>

    init {
        this.name = name
        this.description = description
        this.muscles = ArrayList<Muscle>()
        for(muscle in muscles){
            (this.muscles as ArrayList<Muscle>).add(muscle)
        }
        this.musclesSecond = ArrayList<Muscle>()
        for(muscle in musclesSecond){
            (this.musclesSecond as ArrayList<Muscle>).add(muscle)
        }
    }
}

