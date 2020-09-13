package com.shenzou.workoutchecker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.shenzou.workoutchecker.R
import com.shenzou.workoutchecker.objects.Seance

class SeancesAdapter(items: ArrayList<Seance>, ctx: Context): ArrayAdapter<Seance>(ctx,
    R.layout.item_seance_list, items) {

    private class SeanceItemViewHolder{
        var name: TextView? = null
        var date: TextView? = null
        var nbSeries: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val viewHolder: SeanceItemViewHolder

        if(view == null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_seance_list, parent, false)

            viewHolder = SeanceItemViewHolder()
            viewHolder.name = view!!.findViewById<View>(R.id.titleText) as TextView
            viewHolder.date = view.findViewById<View>(R.id.dateText) as TextView
            viewHolder.nbSeries = view.findViewById<View>(R.id.nbSeries) as TextView

        } else {
            viewHolder = view.tag as SeanceItemViewHolder
        }

        val seance = getItem(position)
        viewHolder.name!!.text = seance!!.name
        viewHolder.date!!.text = seance.dateStr
        viewHolder.nbSeries!!.text = seance.listSeries.size.toString()

        view.tag = viewHolder
        return view
    }
}