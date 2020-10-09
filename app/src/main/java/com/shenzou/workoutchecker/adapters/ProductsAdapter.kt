package com.shenzou.workoutchecker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shenzou.workoutchecker.R
import com.shenzou.workoutchecker.objects.ProductData
import com.shenzou.workoutchecker.objects.ProductElement
import kotlinx.android.synthetic.main.item_food_list.view.*

class ProductsAdapter(val items: ArrayList<ProductElement>, val ctx: Context): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var onItemClick: ((ProductElement) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_food_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quantityInt = items[position].quantity.toString().toInt()

        var energy: Double = items[position].productData.product.nutriments.energy?.div(100) ?: 0.0
        energy = energy.times(quantityInt)

        var proteins: Double = items[position].productData.product.nutriments.proteins?.div(100) ?: 0.0
        proteins = proteins.times(quantityInt)

        var glucides: Double = items[position].productData.product.nutriments.carbohydrates?.div(100) ?: 0.0
        glucides = glucides.times(quantityInt)

        holder.name?.text = items[position].productData.product.product_name_fr
        /*holder.calories?.text = items[position].productData.product.nutriments.energy.toString()
        holder.proteines?.text = items[position].productData.product.nutriments.proteins.toString()
        holder.glucides?.text = items[position].productData.product.nutriments.carbohydrates.toString()*/
        holder.calories?.text = energy.toString()
        holder.proteines?.text = proteins.toString()
        holder.glucides?.text = glucides.toString()
        holder.quantity?.text = items[position].quantity.toString()

        Glide.with(ctx)
            .load(items[position].productData.product.image_front_small_url)
            .into(holder.iconProduct)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name = view.productName
        val calories = view.calories
        val proteines = view.proteines
        val glucides = view.glucides
        val iconProduct = view.iconProduct
        val quantity = view.quantity

        init{
            view.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}