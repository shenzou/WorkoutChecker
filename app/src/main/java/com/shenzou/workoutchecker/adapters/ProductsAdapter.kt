package com.shenzou.workoutchecker.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shenzou.workoutchecker.R
import com.shenzou.workoutchecker.objects.ProductElement
import kotlinx.android.synthetic.main.item_food_list.view.*
import kotlin.math.roundToInt

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
        energy = ((energy * 100).roundToInt() /100.00)

        var proteins: Double = items[position].productData.product.nutriments.proteins?.div(100) ?: 0.0
        proteins = proteins.times(quantityInt)
        proteins = ((proteins * 100).roundToInt() /100.00)

        var glucides: Double = items[position].productData.product.nutriments.carbohydrates?.div(100) ?: 0.0
        glucides = glucides.times(quantityInt)
        glucides = ((glucides * 100).roundToInt() /100.00)

        var fat: Double = items[position].productData.product.nutriments.fat?.div(100) ?: 0.0
        fat = fat.times(quantityInt)
        fat = ((fat * 100).roundToInt() /100.00)

        var saturated: Double = fat.times(quantityInt)
        saturated = ((saturated * 100).roundToInt() /100.00)

        if(items[position].productData.product.product_name_fr != ""){
            holder.name?.text = items[position].productData.product.product_name_fr
        } else if(items[position].productData.product.product_name_en != ""){
            holder.name?.text = items[position].productData.product.product_name_en
        } else{
            holder.name?.text = items[position].productData.product.product_name
        }

        /*holder.calories?.text = items[position].productData.product.nutriments.energy.toString()
        holder.proteines?.text = items[position].productData.product.nutriments.proteins.toString()
        holder.glucides?.text = items[position].productData.product.nutriments.carbohydrates.toString()*/
        holder.calories?.text = energy.toString()
        holder.proteines?.text = proteins.toString()
        holder.glucides?.text = glucides.toString()
        holder.quantity?.text = items[position].quantity.toString()
        holder.fat?.text = fat.toString()
        holder.saturated?.text = saturated.toString()

        holder.progressCalories?.max = 2500
        holder.progressCalories?.progress = energy.toInt()
        holder.progressCalories?.progressDrawable!!.setColorFilter(this.ctx.resources.getColor(R.color.progressBarBlue), PorterDuff.Mode.SRC_IN)

        holder.progressProteins?.max = 120
        holder.progressProteins?.progress = proteins.toInt()
        holder.progressProteins?.progressDrawable!!.setColorFilter(this.ctx.resources.getColor(R.color.progressBarGreen), PorterDuff.Mode.SRC_IN)

        holder.progressCarbs?.max = 1300
        holder.progressCarbs?.progress = glucides.toInt()
        holder.progressCarbs?.progressDrawable!!.setColorFilter(this.ctx.resources.getColor(R.color.progressBarRed), PorterDuff.Mode.SRC_IN)

        holder.progressFat?.max = 100
        holder.progressFat?.progress = fat.toInt()
        holder.progressFat?.progressDrawable!!.setColorFilter(this.ctx.resources.getColor(R.color.progressBarYellow), PorterDuff.Mode.SRC_IN)




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
        val fat = view.fat
        val saturated = view.saturated
        val progressCalories = view.progressBarCalories
        val progressProteins = view.progressBarProteins
        val progressCarbs = view.progressBarCarbs
        val progressFat = view.progressBarFat

        init{
            view.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}