package com.shenzou.workoutchecker.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shenzou.workoutchecker.fragment_alimentation
import com.shenzou.workoutchecker.fragment_modele
import com.shenzou.workoutchecker.fragment_progress
import com.shenzou.workoutchecker.fragment_seances

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
            3 -> {
                fragment_modele()
            }
            else -> fragment_seances()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}