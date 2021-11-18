package com.qfc.applicationmvvm.randomlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.qfc.applicationmvvm.MainActivity
import com.qfc.applicationmvvm.R
import com.qfc.applicationmvvm.startNewActivity


class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val navController = Navigation.findNavController(this, R.id.listFragmentContainerView)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.listFragmentContainerView),
            null
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startNewActivity(MainActivity::class.java)
    }
}