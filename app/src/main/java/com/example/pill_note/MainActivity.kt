package com.example.pill_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pill_note.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragment = FollowFragment()

        override fun getItemCount(): Int = 1

        override fun createFragment(position: Int): Fragment = fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val adapter = MyFragmentPagerAdapter(this)
        /*
        val menu = listOf("통계", "홈", "팔로우")
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager){ tab, position ->
            tab.text = menu[position]
        }.attach()
        */

        val menu = "팔로우"
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager){ tab, position ->
            tab.text = menu
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_top, menu)

        return super.onCreateOptionsMenu(menu)
    }

}