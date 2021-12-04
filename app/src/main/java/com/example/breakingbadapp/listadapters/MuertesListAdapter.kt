package com.example.breakingbadapp.listadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.databinding.ItemRowDeathBinding
import com.example.breakingbadapp.model.Muertes


class MuertesListAdapter : ListAdapter<Muertes, MuerteViewHolder>(MuerteComparator()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuerteViewHolder {
        return MuerteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MuerteViewHolder, position: Int) {
        val muerte = getItem(position)

        with(holder.binding){
            tvUltimasPalabras.text = """ "${muerte.lastWords}" """
            tvMuerto.text = muerte.death
            tvAsesino.text = muerte.responsible
            tvCausa.text = muerte.cause
        }
    }
}

class MuerteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowDeathBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): MuerteViewHolder {
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowDeathBinding.inflate(layoutInflaterB, parent, false)

            return MuerteViewHolder(binding.root)
        }
    }

}

class MuerteComparator : DiffUtil.ItemCallback<Muertes>() {
    override fun areItemsTheSame(oldItem: Muertes, newItem: Muertes): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Muertes, newItem: Muertes): Boolean {
        return oldItem.id == newItem.id
    }
}