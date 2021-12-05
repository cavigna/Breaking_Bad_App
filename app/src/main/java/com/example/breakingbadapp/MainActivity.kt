package com.example.breakingbadapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.breakingbadapp.app.BbApp
import com.example.breakingbadapp.databinding.ActivityMainBinding
import com.example.breakingbadapp.viewmodel.BbModelFactory
import com.example.breakingbadapp.viewmodel.BbViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val viewModel by viewModels<BbViewModel> {
        BbModelFactory((application as BbApp).repositorio)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.conteoPersonaje.observe(this,{
            Log.i("caca","Cantidad: , $it")
            if (it!=62){
                viewModel.agregarADb()
            }else{
                viewModel.terminoAgregarDB.value = true
            }



        })


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> navController.navigate(R.id.homeFragment)
                R.id.personajes_menu -> navController.navigate(R.id.personajesFragment)
                R.id.quotes_menu -> navController.navigate(R.id.quotesFragment)
                R.id.muertes_menu -> navController.navigate(R.id.muertesFragment)

            }
            true
        }

    }
}