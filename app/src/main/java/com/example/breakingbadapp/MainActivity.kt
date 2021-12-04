package com.example.breakingbadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.breakingbadapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu-> navController.navigate(R.id.homeFragment)
                R.id.personajes_menu-> navController.navigate(R.id.personajesFragment)
                R.id.quotes_menu-> navController.navigate(R.id.quotesFragment)
                R.id.muertes_menu -> navController.navigate(R.id.muertesFragment)

            }
            true
        }

    }
}