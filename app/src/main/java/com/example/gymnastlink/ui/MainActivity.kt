package com.example.gymnastlink.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gymnastlink.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentTitle: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentTitle = findViewById(R.id.fragment_title)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        val navHostController: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.main_nav_controller) as? NavHostFragment
        navController = navHostController?.navController!!
        navController.let { NavigationUI.setupWithNavController(bottomNavigation, it) }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
        }
    }

    fun setFragmentTitle(title: String) {
        fragmentTitle.text = title
    }

    fun showReturnButtonOnToolbar(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        val startAlpha = if (show) 0f else 1f
        val endAlpha = if (show) 1f else 0f
        val animator = ValueAnimator.ofFloat(startAlpha, endAlpha)
        animator.addUpdateListener { animation ->
            val alpha = animation.animatedValue as Float
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            toolbar.navigationIcon?.alpha = (alpha * 255).toInt()
        }
        animator.duration = 300 // duration in milliseconds
        animator.start()
    }

    fun showBottomNavigation(visible: Boolean) {
        if (visible) {
            bottomNavigation.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(300)
                    .start()
            }
        } else {
            bottomNavigation.animate()
                .alpha(0f)
                .translationY(bottomNavigation.height.toFloat())
                .setDuration(300)
                .withEndAction { bottomNavigation.visibility = View.GONE }
                .start()
        }
    }
}