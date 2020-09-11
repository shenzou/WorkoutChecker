package com.shenzou.workoutchecker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SeriesAdapter(items: ArrayList<Serie>, ctx: Context): ArrayAdapter<Serie>(ctx, R.layout.item_serie_list, items) {

    private class SerieItemViewHolder{
        internal var name: TextView? = null
        internal var reps: TextView? = null
        internal var charge: TextView? = null
        internal var imageCtn: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        var viewHolder: SerieItemViewHolder

        /*if(view == null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_serie_list, parent, false)

            viewHolder = SerieItemViewHolder()
            viewHolder.name = view!!.findViewById<View>(R.id.exerciceName) as TextView
            viewHolder.reps = view!!.findViewById<View>(R.id.reps) as TextView
            viewHolder.charge = view!!.findViewById<View>(R.id.charge) as TextView
            viewHolder.imageCtn = view!!.findViewById<View>(R.id.musclesImage) as ImageView
        } else {
            viewHolder = view.tag as SerieItemViewHolder
        }*/

        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.item_serie_list, parent, false)

        viewHolder = SerieItemViewHolder()
        viewHolder.name = view!!.findViewById<View>(R.id.exerciceName) as TextView
        viewHolder.reps = view.findViewById<View>(R.id.reps) as TextView
        viewHolder.charge = view.findViewById<View>(R.id.charge) as TextView
        viewHolder.imageCtn = view.findViewById<View>(R.id.musclesImage) as ImageView

        val serie = getItem(position)
        viewHolder.name!!.text = serie!!.exercice.name
        viewHolder.reps!!.text = serie.reps.toString()
        viewHolder.charge!!.text = serie.poids.toString()

        val arrayImages: ArrayList<Bitmap> = ArrayList()

        val requiredWidth = 125 * parent.resources.displayMetrics.density
        var sampleSize = (1920 / requiredWidth).toInt()
        if(sampleSize < 1) sampleSize = 1
        val options = BitmapFactory.Options().apply {
            inSampleSize = sampleSize
        }

        for(muscle in serie.exercice.muscles){
            val image: Bitmap = BitmapFactory.decodeResource(context.resources, muscle.imageRes, options)
            arrayImages.add(image)
        }
        for(muscle in serie.exercice.musclesSecond){
            val image: Bitmap = BitmapFactory.decodeResource(context.resources, muscle.imageResSecondary, options)
            arrayImages.add(image)
        }
        var finalImage: Bitmap = arrayImages.get(0)
        if(arrayImages.size > 1){
            for(i in 1 until arrayImages.size){
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
}