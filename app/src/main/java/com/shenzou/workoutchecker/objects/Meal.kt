package com.shenzou.workoutchecker.objects

import java.io.Serializable

class ProductElement(var productData: ProductData, var quantity: Int): Serializable

class Meal(name: String, date: String): Serializable {
    var name = ""
    var date = ""
    var listProducts: ArrayList<ProductElement> = ArrayList()

    init{
        this.name = name
        this.date = date
    }

    fun productsEANToString(): String{
        var eans = ""
        for(product in listProducts){
            eans += product.productData.code + ";"
        }
        return eans
    }

    fun productsQuantitiesToString(): String{
        var quantities = ""
        for(quantity in listProducts){
            quantities += quantity.quantity.toString() + ";"
        }
        return quantities
    }
}