package com.example.breakingbadapp.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.breakingbadapp.R
import com.example.breakingbadapp.app.BbApp
import com.example.breakingbadapp.databinding.FragmentHomeBinding
import com.example.breakingbadapp.model.Personaje
import com.example.breakingbadapp.viewmodel.BbModelFactory
import com.example.breakingbadapp.viewmodel.BbViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import java.lang.NullPointerException


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var application: Application

    private val viewModel by activityViewModels<BbViewModel> {
        BbModelFactory((application as BbApp).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = requireActivity().application



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)








        viewModel.terminoAgregarDB.observe(viewLifecycleOwner, { termino ->
            when (termino) {

                true -> viewModel.personajeRandom.observe(viewLifecycleOwner, {
                    with(binding) {
                        binding.cardHome.visibility = View.VISIBLE
                        binding.progessIndicator.visibility = View.GONE

                        imagePersonajeHome.load(it.img) {
                            transformations(RoundedCornersTransformation(10f))
                        }
                        tvNombrePersonajeHome.text = it.name
                        val profesion = it.occupation.joinToString { it }
                        tvProfesionHome.text = "Profesion: ${profesion}"


                    }
                })

                false ->{
                    val progress = binding.progessIndicator

                    progress.visibility = View.VISIBLE
                    binding.cardHome.visibility = View.GONE
                }
            }
        })

        binding.cardHome.setOnClickListener {
            val personaje = viewModel.nuevoPersonajeRandom(id = (1..62).random())

            personaje.observe(viewLifecycleOwner,{
                with(binding) {
                    binding.cardHome.visibility = View.VISIBLE
                    binding.progessIndicator.visibility = View.GONE

                    imagePersonajeHome.load(it.img) {
                        transformations(RoundedCornersTransformation(10f))
                    }
                    tvNombrePersonajeHome.text = it.name
                    val profesion = it.occupation.joinToString { it }
                    tvProfesionHome.text = "Profesion: ${profesion}"


                }
            })

        }



        return binding.root
    }

//fun crearTarjeta(personaje:Personaje){
//    with(binding){
//        imagePersonajeHome.load(personaje.img) {
//            transformations(RoundedCornersTransformation(10f))
//        }
//        tvNombrePersonajeHome.text = personaje.name
//        val profesion = personaje.occupation.joinToString { it }
//        tvProfesionHome.text = "Profesion: ${profesion}"
//    }
//}

}