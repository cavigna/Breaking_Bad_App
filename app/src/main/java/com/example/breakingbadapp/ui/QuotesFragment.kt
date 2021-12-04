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
import com.example.breakingbadapp.databinding.FragmentQuotesBinding
import com.example.breakingbadapp.listadapters.QuotesListAdapter
import com.example.breakingbadapp.viewmodel.BbModelFactory
import com.example.breakingbadapp.viewmodel.BbViewModel


class QuotesFragment : Fragment() {
    private lateinit var binding: FragmentQuotesBinding
    private lateinit var application: Application

    private val  viewModel by activityViewModels<BbViewModel> {
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
        binding = FragmentQuotesBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.recyclerViewQuotes
        val adapter = QuotesListAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listadoFrases.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })


        return binding.root
    }


}