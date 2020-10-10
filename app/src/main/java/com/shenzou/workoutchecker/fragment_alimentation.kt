package com.shenzou.workoutchecker

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class fragment_alimentation : Fragment() {

    private lateinit var viewOfLayout: View

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

        //val rv = view.findViewById<RecyclerView>(R.id.recyclerview)
        //rv.layoutManager = LinearLayoutManager(view.context)
        //rv.adapter =
        /**A changer **/

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener{_, year, month, dayOfMonth ->
            val date = year.toString() + "/" + (month+1) + "/" + dayOfMonth.toString()
            val intent = Intent(this.context, FoodDayActivity::class.java)
            intent.putExtra("date", date)
            startActivity(intent)
        })


        val buttonAdd: Button = view.findViewById(R.id.button_add)
        buttonAdd.setOnClickListener(){
            /** A changer **/
            /*val intent = Intent(this.context, FoodDayActivity::class.java)
            startActivity(intent)*/

            val dialogView = LayoutInflater.from(it.context).inflate(R.layout.alert_new_dayfood, null)
            val dateSelect = dialogView.findViewById<TextView>(R.id.editTextDate)
            dateSelect.text = SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis())

            val calendarButton = dialogView.findViewById<ImageButton>(R.id.calendarButton)
            calendarButton.setOnClickListener(){
                val c = Calendar.getInstance()
                val y = c.get(Calendar.YEAR)
                val m = c.get(Calendar.MONTH)
                val d = c.get(Calendar.DAY_OF_MONTH)
                val dp: DatePickerDialog =
                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                        DatePickerDialog(it.context, R.style.MySpinnerDatePickerStyle,
                            { _, year, monthOfYear, dayOfMonth ->
                                dateSelect.text = "$year/${monthOfYear+1}/$dayOfMonth"
                            }, y, m, d)
                    } else{
                        DatePickerDialog(it.context, { _, year, monthOfYear, dayOfMonth ->
                            dateSelect.text = "$year/${monthOfYear+1}/$dayOfMonth"
                        }, y, m, d)
                    }

                dp.show()
            }
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Ajouter jour")
            builder.setView(dialogView)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

            val valider:Button = dialogView.findViewById(R.id.valider)
            valider.setOnClickListener(){
                val intent = Intent(this.context, FoodDayActivity::class.java)
                intent.putExtra("date", dateSelect.text.toString())
                startActivity(intent)
            }
            val annuler:Button = dialogView.findViewById(R.id.annuler)
            annuler.setOnClickListener(){
                alertDialog.cancel()
            }
        }


    }
}