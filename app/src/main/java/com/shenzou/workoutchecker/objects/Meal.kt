package com.shenzou.workoutchecker.objects

import java.io.Serializable

class Meal(name: String, date: String): Serializable {
    var name = ""
    var date = ""
    var listProducts: List<ProductData> = ArrayList()

    init{
        this.name = name
        this.date = date
    }

    fun productsEANToString(): String{
        var eans = ""
        for(product in listProducts){
            eans += product.code + ";"
        }
        return eans
    }
}