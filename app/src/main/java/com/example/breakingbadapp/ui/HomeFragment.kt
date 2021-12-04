package com.example.breakingbadapp.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import coil.load
import com.example.breakingbadapp.R
import com.example.breakingbadapp.app.BbApp
import com.example.breakingbadapp.databinding.FragmentHomeBinding
import com.example.breakingbadapp.viewmodel.BbModelFactory
import com.example.breakingbadapp.viewmodel.BbViewModel
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


//        viewModel.fraseRandom.asLiveData().observe(viewLifecycleOwner,{
//           // binding.prueba.text = it.quote
//        })


        viewModel.personajeRandom.observe(viewLifecycleOwner, {
            with(binding){

              //  imagePersonajeHome.load(it.img)
               // tvNombrePersonajeHome.text = it.name
                try {

                }catch (e: NullPointerException){}

            }
        })



        return binding.root
    }


    fun algo(): String {
        val listado = listOf<String>("uno","dos")
        return listado.joinToString {
            it
        }
    }
}