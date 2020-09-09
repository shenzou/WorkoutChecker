package com.shenzou.workoutchecker

import java.io.Serializable

class ModelSeance(val name: String) : Serializable{
    val listSeries: ArrayList<Serie> = ArrayList<Serie>()

    fun AddSerie(serie: Serie){
        listSeries.add(serie)
    }

    fun removeSerie(serie: Serie){
        listSeries.remove(serie)
    }

    fun getAllSeries(): ArrayList<Serie>{
        return listSeries
    }

    fun SeriesToString(): String{
        val series: String = ""
        for(serie in listSeries){
            series.plus(serie.exercice.name).plus(";")
            series.plus(serie.exercice.description).plus(";")
            series.plus(serie.exercice.videoLink).plus(";")
            var index=0
            for(muscle in serie.exercice.muscles){
                if(index>0){
                    series.plus(",")
                }
                series.plus(muscle.name)
                index++
            }
            series.plus(";")
            index=0
            for(muscle in serie.exercice.musclesSecond){
                if(index>0){
                    series.plus(",")
                }
                series.plus(muscle.name)
                index++
            }
            series.plus(";")
            series.plus(serie.reps.toString()).plus(";")
            series.plus(serie.poids.toString())
            series.plus("NEXT")
        }
        return series
    }

    /*
    Ordre de concaténation series:
    - Exercice:
        - Nom ";" 0
        - Description ";" 1
        - videoLink ";" 2
        - muscles "," (comma separated) ";" 3
        - musclesSecond "," (comma separated) ";" 4
     - Reps ";" 5
     - Poids ";" 6
     "NEXT"
     */

}