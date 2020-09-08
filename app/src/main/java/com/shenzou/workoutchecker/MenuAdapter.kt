package com.shenzou.workoutchecker

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MenuAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                fragment_seances()
            }
            1 -> {
                fragment_progress()
            }
            2 -> {
                fragment_alimentation()
            }
            else -> fragment_seances()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}