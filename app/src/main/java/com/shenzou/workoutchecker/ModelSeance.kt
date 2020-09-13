package com.shenzou.workoutchecker

import com.shenzou.workoutchecker.objects.Serie
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
        var series = ""
        if(listSeries.size>0){
            for(serie in listSeries){
                series += serie.exercice.name + ";"
                series += serie.exercice.description + ";"
                series += serie.exercice.videoLink + ";"
                var index=0
                for(muscle in serie.exercice.muscles){
                    if(index>0){
                        series += ","
                    }
                    series += muscle.name
                    index++
                }
                series += ";"
                index=0
                for(muscle in serie.exercice.musclesSecond){
                    if(index>0){
                        series += ","
                    }
                    series += muscle.name
                    index++
                }
                series += ";"
                series += serie.reps.toString() + ";"
                series += serie.poids.toString()
                series += "!"
            }
        } else{
            series = ";;;;;;;!"
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
     "!"
     */

}