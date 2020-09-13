package com.shenzou.workoutchecker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.shenzou.workoutchecker.adapters.ExercicesAdapter
import com.shenzou.workoutchecker.objects.Exercice
import kotlin.math.pow
import kotlin.math.sqrt

class ExercicePicker : AppCompatActivity() {

    //private lateinit var adapter: ExercicesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercice_picker)

        val context = this

        val poids = intent.getStringExtra("poids")
        val reps = intent.getStringExtra("reps")

        var adapter = ExercicesAdapter(MainActivity.exercicesList, this)
        adapter.onItemClick = {exercice ->
            val intent = Intent()
            intent.putExtra("exercice", exercice)
            intent.putExtra("poids", poids)
            intent.putExtra("reps", reps)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        val rv = findViewById<RecyclerView>(R.id.rv)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val x = (dm.widthPixels / dm.xdpi).toDouble().pow(2.0)
        val y = (dm.heightPixels / dm.ydpi).toDouble().pow(2.0)
        val screenInches = sqrt(x+y)

        if(screenInches < 7){
            rv.layoutManager = LinearLayoutManager(this)
        } else{
            rv.layoutManager = GridLayoutManager(this,2)
        }

        rv.adapter = adapter

        val tabLayout: TabLayout = findViewById(R.id.tabLayout2)


        Log.d("Tab tag", tabLayout.getTabAt(0)?.tag.toString())



        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("Tab", "New tab selected")
                val exos: ArrayList<Exercice> = ArrayList()
                if(tab.text == "Tous"){
                    Log.d("Tab", "Tous")
                    for(exo in MainActivity.exercicesList){
                        exos.add(exo)
                    }
                }
                else if(tab.text == "Bras"){
                    Log.d("Tab", "Bras")
                    for(exercice in MainActivity.exercicesList){
                        for(muscle in exercice.muscles){
                            if(muscle.name == "Biceps"
                                || muscle.name == "Triceps"
                                || muscle.name == "Avant-bras"
                            ){
                                exos.add(exercice)
                                break
                            }
                        }
                    }
                }
                else if(tab.text == "Epaules"){
                    Log.d("Tab", "Epaules")
                    for(exercice in MainActivity.exercicesList){
                        for(muscle in exercice.muscles){
                            if(muscle.name == "Epaules"
                                || muscle.name == "Trapezes"
                            ){
                                exos.add(exercice)
                                break
                            }
                        }
                    }
                }
                else if(tab.text == "Dos"){
                    Log.d("Tab", "Dos")
                    for(exercice in MainActivity.exercicesList){
                        for(muscle in exercice.muscles){
                            if(muscle.name == "Dorsaux"
                                || muscle.name == "Lombaires"
                            ){
                                exos.add(exercice)
                                break
                            }
                        }
                    }
                }
                else if(tab.text == "Pectoraux"){
                    Log.d("Tab", "Pecs")
                    for(exercice in MainActivity.exercicesList){
                        for(muscle in exercice.muscles){
                            if(muscle.name == "Pectoraux"){
                                exos.add(exercice)
                                break
                            }
                        }
                    }
                }
                else if(tab.text == "Abdominaux"){
                    Log.d("Tab", "Abdos")
                    for(exercice in MainActivity.exercicesList){
                        for(muscle in exercice.muscles){
                            if(muscle.name == "Abdominaux"){
                                exos.add(exercice)
                                break
                            }
                        }
                    }
                }
                else if(tab.text == "Jambes"){
                    Log.d("Tab", "Jambes")
                    for(exercice in MainActivity.exercicesList){
                        for(muscle in exercice.muscles){
                            if(muscle.name == "Quadriceps"
                                || muscle.name == "Fessiers"
                                || muscle.name == "Ischio-jambiers"
                                || muscle.name == "Mollets"
                            ){
                                exos.add(exercice)
                                break
                            }
                        }
                    }
                }
                else{
                    Log.d("None", tab.tag.toString())
                }
                adapter = ExercicesAdapter(exos, context)
                adapter.onItemClick = {exercice ->
                    val intent = Intent()
                    intent.putExtra("exercice", exercice)
                    intent.putExtra("poids", poids)
                    intent.putExtra("reps", reps)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                rv.adapter = adapter

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        //initAdapter(poids, reps, MainActivity.exercicesList)



    }

}