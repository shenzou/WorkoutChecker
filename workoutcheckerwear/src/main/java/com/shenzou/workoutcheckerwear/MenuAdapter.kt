package com.shenzou.workoutcheckerwear

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(val items: ArrayList<MenuItem>, val ctx: Context): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var onItemClick: ((MenuItem) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    public fun itemCount(): Int{
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_menu_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.icon.setImageResource(items[position].image)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val icon: ImageView = view.findViewById(R.id.icon)

        init{
            view.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }



}