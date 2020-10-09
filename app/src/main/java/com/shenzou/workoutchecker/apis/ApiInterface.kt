package com.shenzou.workoutchecker.apis

import com.shenzou.workoutchecker.objects.ProductData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    fun getProduct(@Url url: String): Call<ProductData>

    companion object{
        var BASE_URL = "https://world.openfoodfacts.org/api/v0/product/"
        //var BASE_URL = "http://192.168.0.16:3000/products/"

        fun create(): ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}