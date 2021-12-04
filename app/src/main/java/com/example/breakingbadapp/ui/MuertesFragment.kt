package com.example.breakingbadapp.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.app.BbApp
import com.example.breakingbadapp.databinding.FragmentMuertesBinding
import com.example.breakingbadapp.listadapters.MuertesListAdapter
import com.example.breakingbadapp.viewmodel.BbModelFactory
import com.example.breakingbadapp.viewmodel.BbViewModel


class MuertesFragment : Fragment() {
    private lateinit var binding: FragmentMuertesBinding
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
        binding = FragmentMuertesBinding.inflate(layoutInflater, container, false)

        val recycler = binding.recyclerView
        val adapter = MuertesListAdapter()

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listadoMuertes.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })


        return binding.root
    }


}