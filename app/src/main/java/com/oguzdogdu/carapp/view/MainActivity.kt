package com.oguzdogdu.carapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.oguzdogdu.carapp.R
import com.oguzdogdu.carapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        val navController = findNavController(this, R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)


    }
}

