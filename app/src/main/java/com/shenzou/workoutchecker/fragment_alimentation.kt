package com.shenzou.workoutchecker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shenzou.workoutchecker.adapters.ProductsAdapter
import com.shenzou.workoutchecker.apis.ApiInterface
import com.shenzou.workoutchecker.objects.Exercice
import com.shenzou.workoutchecker.objects.ProductData
import com.shenzou.workoutchecker.objects.Serie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class fragment_alimentation : Fragment() {

    private lateinit var viewOfLayout: View
    val listProducts: ArrayList<ProductData> = ArrayList()
    private val SCAN_RESULT_CODE = 5
    private lateinit var adapter: ProductsAdapter

    companion object{
        fun newInstance(): fragment_alimentation{
            return fragment_alimentation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewOfLayout =  inflater.inflate(R.layout.fragment_alimentation, container, false)
        return viewOfLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val textView: TextView = view.findViewById(R.id.textView)

        adapter = ProductsAdapter(listProducts, view.context)
        val rv = view.findViewById<RecyclerView>(R.id.recyclerview)
        rv.layoutManager = LinearLayoutManager(view.context)
        rv.adapter = adapter


        val buttonAdd: Button = view.findViewById(R.id.button_add)
        buttonAdd.setOnClickListener(){
            val intent = Intent(this.context, BarcodeScanActivity::class.java)
            startActivityForResult(intent, SCAN_RESULT_CODE)

        }


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
                                    listProducts.add(prod)
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