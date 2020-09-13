package com.shenzou.workoutchecker.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shenzou.workoutchecker.R
import com.shenzou.workoutchecker.objects.Exercice
import kotlinx.android.synthetic.main.item_exercice_list.view.*

class ExercicesAdapter(val items: ArrayList<Exercice>, val ctx: Context): RecyclerView.Adapter<ExercicesAdapter.ViewHolder>() {

    var onItemClick: ((Exercice) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_exercice_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = items[position].name
        holder.description?.text = items[position].description


        try{
            var musclesPrime = ""
            for((index, muscle) in items[position].muscles.withIndex()){
                if(index>0){
                    musclesPrime += ", "
                }
                musclesPrime += muscle.name
            }
            holder.muscles?.text = musclesPrime
            //holder?.muscles?.text = items.get(position).muscles.get(0).name
        }
        catch (e: Exception){
            holder.muscles?.text = ""
        }


        try{
            var musclesS = ""
            for((index, muscle) in items.get(position).musclesSecond.withIndex()) {
                if (index > 0) {
                    musclesS += ", "
                }
                musclesS += muscle.name
            }
            holder.musclesSecond?.text = musclesS
            //holder?.musclesSecond?.text = items.get(position).musclesSecond.get(0).name
        } catch(e: Exception){
            holder.musclesSecond?.text = ""
        }

        val arrayImages: ArrayList<Bitmap> = ArrayList()

        val requiredWidth = 145 * ctx.resources.displayMetrics.density
        var sampleSize = (1920 / requiredWidth).toInt()
        if(sampleSize < 1) sampleSize = 1
        val options = BitmapFactory.Options().apply {
            inSampleSize = sampleSize
        }
        //var index = 0
        for(muscle in items[position].muscles){
            val image: Bitmap = BitmapFactory.decodeResource(ctx.resources, muscle.imageRes, options)
            /*if(index == 0){
                //val originalHeight = options.outHeight
                val originalWidth = options.outWidth
                val requiredWidth = 200 * ctx.resources.displayMetrics.density
                var sampleSize = (originalWidth / requiredWidth).toInt()
                if(sampleSize < 1) sampleSize = 1
                Log.d("Size", sampleSize.toString())
                options = BitmapFactory.Options().apply {
                    inJustDecodeBounds = false
                    options.inSampleSize = sampleSize
                }
                image = BitmapFactory.decodeResource(ctx.resources, muscle.imageRes, options)
            }*/
            arrayImages.add(image)
            //index++
        }
        for(muscle in items[position].musclesSecond){
            val image: Bitmap = BitmapFactory.decodeResource(ctx.resources, muscle.imageResSecondary, options)
            arrayImages.add(image)
        }

        var finalImage: Bitmap = arrayImages[0]
        if(arrayImages.size > 1){
            for(i in 1 until arrayImages.size){
                finalImage = createSingleImageFromMultipleImages(finalImage, arrayImages[i])
            }
        }

        Glide.with(ctx).load(finalImage).into(holder.imageCtn)
        //holder?.imageCtn?.setImageBitmap(finalImage)
    }

    fun createSingleImageFromMultipleImages(firstImage: Bitmap, secondImage: Bitmap): Bitmap{
        val result: Bitmap = Bitmap.createBitmap(firstImage.width, firstImage.height, firstImage.config)
        val canvas = Canvas(result)
        canvas.drawBitmap(firstImage, 0f, 0f, null)
        canvas.drawBitmap(secondImage, 0f, 0f, null)
        return result
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.exerciceName
        val description = view.description
        val muscles = view.muscles
        val musclesSecond = view.musclesSecond
        val imageCtn = view.imageView

        init {
            view.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
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
