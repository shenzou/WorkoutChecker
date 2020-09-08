package com.shenzou.workoutchecker

class Exercice(name: String, description: String, muscles: List<Muscle>, musclesSecond: List<Muscle>) {
    var name = ""
    var description = ""
    var videoLink = ""
    var icon = ""
    var muscles : List<Muscle>
    var musclesSecond : List<Muscle>

    init {
        this.name = name
        this.description = description
        this.muscles = muscles
        this.musclesSecond = musclesSecond
    }
}

