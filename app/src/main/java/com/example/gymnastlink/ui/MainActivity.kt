package com.example.gymnastlink.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.gymnastlink.R
import com.example.gymnastlink.ui.fragments.UpdatesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        fragmentTitle = findViewById(R.id.fragment_title)
        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.updates_page_item -> {
                        loadFragment(UpdatesFragment())
                        true
                    }
                    R.id.workouts_page_item -> {
                        loadFragment(UpdatesFragment())
                        true
                    }
                    R.id.nutrition_page_item -> {
                        loadFragment(UpdatesFragment())
                        true
                    }
                    R.id.profile_page_item -> {
                        loadFragment(UpdatesFragment())
                        true
                    }

                    else -> false
                }
            }

            selectedItemId = R.id.updates_page_item
        }
    }

    fun updateFragmentTitle(title: String) {
        fragmentTitle.text = title
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}