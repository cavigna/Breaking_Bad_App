package com.example.breakingbadapp.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.app.BbApp
import com.example.breakingbadapp.databinding.FragmentPersonajesBinding
import com.example.breakingbadapp.listadapters.PersonajeListAdapter
import com.example.breakingbadapp.utils.hideKeyboard
import com.example.breakingbadapp.viewmodel.BbModelFactory
import com.example.breakingbadapp.viewmodel.BbViewModel

class PersonajesFragment : Fragment() {
    private lateinit var binding: FragmentPersonajesBinding
    private lateinit var application: Application
    private lateinit var adapter: PersonajeListAdapter
    private lateinit var searchView: SearchView

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
        binding = FragmentPersonajesBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.recyclerPersonajes
        adapter = PersonajeListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listadoPersonaje.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        val searchView = binding.searchViewPersonaje

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchDB(query)

                }
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchDB(newText)
                }
                return true

            }

        })


        return binding.root
    }

    private fun searchDB(newText: String) {
        viewModel.buscarPersonaje(newText).observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

    }


}