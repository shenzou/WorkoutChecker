package com.shenzou.workoutchecker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shenzou.workoutchecker.adapters.ProductsAdapter
import com.shenzou.workoutchecker.apis.ApiInterface
import com.shenzou.workoutchecker.objects.Meal
import com.shenzou.workoutchecker.objects.ProductData
import com.shenzou.workoutchecker.objects.ProductElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FoodMealActivity : AppCompatActivity() {

    //val listProducts: ArrayList<ProductElement> = ArrayList()
    private lateinit var meal: Meal
    private val SCAN_RESULT_CODE = 5
    private lateinit var adapter: ProductsAdapter
    private var exists = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_meal)

        meal = intent.getSerializableExtra("meal") as Meal
        exists = intent.getBooleanExtra("exists", false)
        title = meal.name

        adapter = ProductsAdapter(meal.listProducts, this)

        val rv = findViewById<RecyclerView>(R.id.recyclerview)
        rv.layoutManager = LinearLayoutManager(this)

        adapter.onItemClick = {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_food_portion, null)
            val annuler = dialogView.findViewById<Button>(R.id.cancelButton)
            val valider = dialogView.findViewById<Button>(R.id.validateButton)
            val portion = dialogView.findViewById<EditText>(R.id.portion)
            var product = it

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Modifier portion")
            builder.setView(dialogView)
            val alertDialog = builder.create()
            alertDialog.show()

            valider.setOnClickListener(){
                product.quantity = portion.text.toString().toInt()
                adapter.notifyDataSetChanged()
                alertDialog.dismiss()
            }

            annuler.setOnClickListener(){
                alertDialog.cancel()
            }
        }
        rv.adapter = adapter


        val buttonAdd: Button = findViewById(R.id.button_add)
        buttonAdd.setOnClickListener(){
            val intent = Intent(this, BarcodeScanActivity::class.java)
            startActivityForResult(intent, SCAN_RESULT_CODE)

        }
    }

    override fun onDestroy() {

        if(exists){
            val dbHandler = DBManager(this, null)
            dbHandler.updateMeal(meal)
            dbHandler.close()
            Log.d("Infos", "Updated meal in db")
        } else{
            val dbHandler = DBManager(this, null)
            dbHandler.addMeal(meal)
            dbHandler.close()
            Log.d("Infos", "Added new meal to db")
        }


        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SCAN_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                val productId: String = data!!.getSerializableExtra("product") as String

                val apiInterface = ApiInterface.create().getProduct("$productId.json")

                apiInterface.enqueue(object : Callback<ProductData> {
                    override fun onResponse(call: Call<ProductData>?, response: Response<ProductData>?) {

                        if(response?.body() != null){
                            try{
                                val prod: ProductData = response.body() as ProductData
                                if(prod.code != "") {
                                    val newProdElement = ProductElement(prod, 100)
                                    meal.listProducts.add(newProdElement)
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

                // Get String data from Intent
                //val returnString = data!!.getStringExtra("keyName")

                // Set text view with string
                //val textView = findViewById(R.id.textView) as TextView
                //textView.text = returnString
            }
        }
    }
}