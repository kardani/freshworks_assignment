package com.kardani.masgiphy.ui

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kardani.masgiphy.ui.giphs.IndexFragment

class PagerAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val fragment = if(position == 0) IndexFragment() else IndexFragment()

//        // Return a NEW fragment instance in createFragment(int)
//        val fragment = DemoObjectFragment()
//        fragment.arguments = Bundle().apply {
//            // Our object is just an integer :-P
//            putInt(ARG_OBJECT, position + 1)
//        }
        return fragment
    }
}