package com.shenzou.workoutchecker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shenzou.workoutchecker.R
import com.shenzou.workoutchecker.objects.ProductData
import kotlinx.android.synthetic.main.item_food_list.view.*

class ProductsAdapter(val items: ArrayList<ProductData>, val ctx: Context): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var onItemClick: ((ProductData) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_food_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = items[position].product.product_name_fr
        holder.calories?.text = items[position].product.nutriments.energy.toString()
        holder.proteines?.text = items[position].product.nutriments.proteins.toString()
        holder.glucides?.text = items[position].product.nutriments.carbohydrates.toString()

        Glide.with(ctx)
            .load(items[position].product.image_front_small_url)
            .into(holder.iconProduct)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name = view.productName
        val calories = view.calories
        val proteines = view.proteines
        val glucides = view.glucides
        val iconProduct = view.iconProduct

        init{
            view.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}