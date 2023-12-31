package com.example.pill_note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pill_note.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.play.integrity.internal.t

class MainActivity : AppCompatActivity() {

    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

        val fragments: List<Fragment>

        init {
            fragments =
                listOf(HomeFragment(), PillAddFragment(), CalendarFragment(), FollowFragment())
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val adapter = MyFragmentPagerAdapter(this)

        val menu = listOf("홈", "추가", "통계", "팔로우")
        binding.viewpager.adapter = adapter

        binding.viewpager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->

            tab.text = menu[position]
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_top, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_chatbot -> {
                val intent = Intent(
                    this, ChatActivity::class.java
                )
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}