package com.example.marvel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)

        swipeRefreshLayout = findViewById(R.id.swipe)
        textView = findViewById(R.id.textView)
        swipeRefreshLayout.setOnRefreshListener {
            number++
            textView.text = " Total number = $number"
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 4000)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun share(view: View){

    }
}