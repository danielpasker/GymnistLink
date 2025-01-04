package com.example.gymnastlink.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gymnastlink.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentTitle: TextView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        fragmentTitle = findViewById(R.id.fragment_title)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        val navHostController: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.main_nav_controller) as? NavHostFragment
        navController = navHostController?.navController!!
        navController.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it
            )
        }
        navController.let { NavigationUI.setupWithNavController(bottomNavigation, it) }
    }

    fun updateFragmentTitle(title: String) {
        fragmentTitle.text = title
    }
}