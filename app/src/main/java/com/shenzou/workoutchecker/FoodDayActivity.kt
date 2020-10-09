package com.shenzou.workoutchecker

import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shenzou.workoutchecker.adapters.MealsAdapter
import com.shenzou.workoutchecker.apis.ApiInterface
import com.shenzou.workoutchecker.objects.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.collections.ArrayList

class FoodDayActivity : AppCompatActivity() {

    private var meals: ArrayList<Meal> = ArrayList()
    private lateinit var adapter: MealsAdapter
    private var date= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_day)

        date = (intent.getSerializableExtra("date") as? String).toString()
        title = date
        adapter = MealsAdapter(meals, this)

        RetrieveMeals()
        for(meal in meals){
            for(product in meal.listProducts){
                RetrieveProductInfos(product)
            }
        }


        val rv = findViewById<RecyclerView>(R.id.recyclerview)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


        val buttonAdd: Button = findViewById(R.id.button_add)
        buttonAdd.setOnClickListener(){
            val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_new_meal, null)
            val annuler = dialogView.findViewById<Button>(R.id.cancelButton)
            val valider = dialogView.findViewById<Button>(R.id.validateButton)
            val name = dialogView.findViewById<EditText>(R.id.editTextMealName)
            val date = dialogView.findViewById<TextView>(R.id.date)

            date.text = title

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ajouter repas")
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.show()

            valider.setOnClickListener(){
                val meal: Meal = Meal(name.text.toString(), date.text.toString())

                val intent = Intent(this, FoodMealActivity::class.java)
                intent.putExtra("meal", meal)
                intent.putExtra("exists", false)
                startActivity(intent)

                alertDialog.dismiss()
            }

            annuler.setOnClickListener(){
                alertDialog.cancel()
            }


        }

        adapter.onItemClick ={
            val intent = Intent(this, FoodMealActivity::class.java)
            intent.putExtra("meal", it)
            intent.putExtra("exists", true)
            startActivity(intent)
        }
    }

    fun RetrieveMeals(){
        val dbHandler = DBManager(this, null)
        val cursor: Cursor? = dbHandler.findMealsAtDate(date)
        //val cursor: Cursor? = dbHandler.findAllMealsSorted(0)

        if(cursor != null && cursor.moveToFirst()){
            Log.d("Found", "meal")
            meals.clear()
            val mealName = cursor.getColumnIndex("name")
            val mealEans = cursor.getColumnIndex("eans")
            val mealDate = cursor.getColumnIndex("date")
            val mealPortions = cursor.getColumnIndex("portions")

            do{
                val name = cursor.getString(mealName)
                val eans = cursor.getString(mealEans)
                val date = cursor.getString(mealDate)
                val portions = cursor.getString(mealPortions)

                val tempMeal = Meal(name, date)

                if(eans.length > 1){
                    val listEans = eans.split(
                        delimiters = *arrayOf(";"),
                        ignoreCase = false,
                        limit = 0
                    )
                    val listPortions = portions.split(
                        delimiters = *arrayOf(";"),
                        ignoreCase = false,
                        limit = 0
                    )

                    var listProds: ArrayList<ProductElement> = ArrayList()

                    var numberOfElements = listEans.size
                    if(listPortions.size < numberOfElements) numberOfElements = listPortions.size
                    for(i in 0 until numberOfElements){
                        var productData = ProductData(listEans[i], Product("", NutrimentsData(), ""))
                        try{
                            var productElement = ProductElement(productData, listPortions[i].toInt())
                            tempMeal.listProducts.add(productElement)
                        } catch(e: Exception){

                        }
                    }
                }
                meals.add(tempMeal)
                adapter.notifyDataSetChanged()
                Log.d("Added", "Meal")
            } while (cursor.moveToNext())
            cursor.close()
        }

        dbHandler.close()
    }

    fun RetrieveProductInfos(productElement: ProductElement){
        val apiInterface = ApiInterface.create().getProduct("${productElement.productData.code}.json")

        apiInterface.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>?, response: Response<ProductData>?) {

                if(response?.body() != null){
                    try{
                        val prod: ProductData = response.body() as ProductData
                        if(prod.code != "") {

                            productElement.productData = prod
                            //meal.listProducts.add(newProdElement)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    catch(e: Exception){

                    }
                    //textView.text = response.body()!!.product.product_name_fr
                    //textView.text = textView.text as String + "\n" + response.body()!!.product.nutriments.energy

                }
                /*if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)*/
            }

            override fun onFailure(call: Call<ProductData>?, t: Throwable?) {

            }

        })
    }


}