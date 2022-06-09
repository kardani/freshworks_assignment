package com.kardani.masgiphy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kardani.masgiphy.R
import com.kardani.masgiphy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = if(position == 0) getString(R.string.giphs) else getString(R.string.favorites)
        }.attach()

    }
}