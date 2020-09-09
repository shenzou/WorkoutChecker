package com.shenzou.workoutchecker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercice_list.view.*

class ExercicesAdapter(val items: ArrayList<Exercice>, val ctx: Context): RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_exercice_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name?.text = items.get(position).name
        holder?.description?.text = items.get(position).description


        try{
            var musclesPrime = ""
            for((index, muscle) in items.get(position).muscles.withIndex()){
                if(index>0){
                    musclesPrime += ", "
                }
                musclesPrime += muscle.name
            }
            holder?.muscles?.text = musclesPrime
            //holder?.muscles?.text = items.get(position).muscles.get(0).name
        }
        catch (e: Exception){
            holder?.muscles?.text = ""
        }


        try{
            var musclesS = ""
            for((index, muscle) in items.get(position).musclesSecond.withIndex()) {
                if (index > 0) {
                    musclesS += ", "
                }
                musclesS += muscle.name
            }
            holder?.musclesSecond?.text = musclesS
            //holder?.musclesSecond?.text = items.get(position).musclesSecond.get(0).name
        } catch(e: Exception){
            holder?.musclesSecond?.text = ""
        }


        val arrayImages: ArrayList<Bitmap> = ArrayList()
        for(muscle in items.get(position).muscles){
            val image: Bitmap = BitmapFactory.decodeResource(ctx.resources, muscle.imageRes)
            arrayImages.add(image)
        }
        var finalImage: Bitmap = arrayImages.get(0)
        if(arrayImages.size > 1){
            for(i in 1 until arrayImages.size-1){
                finalImage = createSingleImageFromMultipleImages(finalImage, arrayImages.get(i))
            }
        }

        holder?.imageCtn?.setImageBitmap(finalImage)
    }

    fun createSingleImageFromMultipleImages(firstImage: Bitmap, secondImage: Bitmap): Bitmap{
        val result: Bitmap = Bitmap.createBitmap(firstImage.width, firstImage.height, firstImage.config)
        val canvas: Canvas = Canvas(result)
        canvas.drawBitmap(firstImage, 0f, 0f, null)
        canvas.drawBitmap(secondImage, 0f, 0f, null)
        return result
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val name = view.exerciceName
    val description = view.description
    val muscles = view.muscles
    val musclesSecond = view.musclesSecond
    val imageCtn = view.imageView
}


/*
class ExercicesAdapter(items: ArrayList<Exercice>, ctx: Context): ArrayAdapter<Exercice>(ctx, R.layout.item_exercice_list, items) {

    private class ExerciceItemViewHolder{
        internal var name: TextView? = null
        internal var description: TextView? = null
        internal var muscles: TextView? = null
        internal var musclesSecond: TextView? = null
        internal var imageCtn: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        var viewHolder: ExerciceItemViewHolder

        if(view == null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_exercice_list, parent, false)

            viewHolder = ExerciceItemViewHolder()
            viewHolder.name = view!!.findViewById<View>(R.id.exerciceName) as? TextView
            viewHolder.description = view!!.findViewById<View>(R.id.description) as? TextView
            viewHolder.muscles = view!!.findViewById<View>(R.id.muscles) as? TextView
            viewHolder.musclesSecond = view!!.findViewById<View>(R.id.musclesSecond) as? TextView
            viewHolder.imageCtn = view!!.findViewById<View>(R.id.imageView) as? ImageView
        } else{
            viewHolder = view.tag as ExerciceItemViewHolder
        }

        val exercice = getItem(position)
        viewHolder.name!!.text = exercice!!.name
        viewHolder.description!!.text = exercice!!.description
        viewHolder.muscles!!.text = exercice!!.MusclesToString()
        viewHolder.musclesSecond!!.text = exercice!!.MusclesSecToString()

        val arrayImages: ArrayList<Bitmap> = ArrayList()
        for(muscle in exercice!!.muscles){
            val image: Bitmap = BitmapFactory.decodeResource(context.resources, muscle.imageRes)
            arrayImages.add(image)
        }
        var finalImage: Bitmap = arrayImages.get(0)
        if(arrayImages.size > 1){
            for(i in 1 until arrayImages.size-1){
                finalImage = createSingleImageFromMultipleImages(finalImage, arrayImages.get(i))
            }
        }

        viewHolder.imageCtn!!.setImageBitmap(finalImage)
        return view
    }

    fun createSingleImageFromMultipleImages(firstImage: Bitmap, secondImage: Bitmap): Bitmap{
        val result: Bitmap = Bitmap.createBitmap(firstImage.width, firstImage.height, firstImage.config)
        val canvas: Canvas = Canvas(result)
        canvas.drawBitmap(firstImage, 0f, 0f, null)
        canvas.drawBitmap(secondImage, 0f, 0f, null)
        return result
    }

}*/
