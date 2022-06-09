package com.kardani.masgiphy.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kardani.masgiphy.ui.favorites.FavoritesFragment
import com.kardani.masgiphy.ui.giphs.IndexFragment

class PagerAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return if (position == 0) IndexFragment() else FavoritesFragment()
    }
}