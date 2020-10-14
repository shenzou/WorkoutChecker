package com.shenzou.workoutcheckerwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView

class MainActivity : WearableActivity() {

    private lateinit var menuElements: ArrayList<MenuItem>
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuElements = ArrayList<MenuItem>()
        menuElements.add(MenuItem(R.drawable.ic_baseline_fitness_center_24, "Seance"))
        menuElements.add(MenuItem(R.drawable.ic_baseline_fitness_center_24, "Seance"))
        menuElements.add(MenuItem(R.drawable.ic_baseline_fitness_center_24, "Seance"))

        adapter = MenuAdapter(menuElements, this)
        Log.d("items in adapter", adapter.itemCount().toString())

        // Enables Always-on
        setAmbientEnabled()

        val wearableRecyclerView: WearableRecyclerView = findViewById(R.id.recycler_launcher_view)

        wearableRecyclerView.apply {
            isEdgeItemsCenteringEnabled = true
            layoutManager = WearableLinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
        wearableRecyclerView.adapter = adapter


    }
}