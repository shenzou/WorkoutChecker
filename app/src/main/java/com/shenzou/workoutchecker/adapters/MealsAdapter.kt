package com.shenzou.workoutchecker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shenzou.workoutchecker.R
import com.shenzou.workoutchecker.objects.Meal
import kotlinx.android.synthetic.main.item_meal_list.view.*
import kotlin.math.roundToInt

class MealsAdapter(val items: ArrayList<Meal>, val ctx: Context): RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    var onItemClick: ((Meal) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_meal_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = items[position].name

        var nbProducts = 0
        var toEnergy = 0
        for(product in items[position].listProducts){
            nbProducts++

            val energy: Int = product.productData.product.nutriments.energy?.roundToInt()!!
            toEnergy += product.quantity.times(energy).div(100)

        }
        holder.totalProducts?.text = nbProducts.toString()
        holder.totalEnergy?.text = toEnergy.toString()

        holder.date?.text = items[position].date

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name = view.mealName
        val date = view.mealDate
        val totalProducts = view.mealTotalProducts
        val totalEnergy = view.mealEnergy

        init{
            view.setOnClickListener(){
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}