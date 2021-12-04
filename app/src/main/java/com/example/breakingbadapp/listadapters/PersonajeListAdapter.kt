package com.example.breakingbadapp.listadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.breakingbadapp.databinding.ItemRowPersonajeBinding
import com.example.breakingbadapp.model.Personaje

class PersonajeListAdapter(): ListAdapter<Personaje, PersonajeViewHolder>(PersonajeComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        return PersonajeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = getItem(position)

        with(holder.binding){
            imageViewRow.load(personaje.img)
            tvNombreRow.text = personaje.name
        }
    }
}

class PersonajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemRowPersonajeBinding.bind(itemView)

    companion object{
        fun create(parent: ViewGroup): PersonajeViewHolder{
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowPersonajeBinding.inflate(layoutInflaterB, parent, false)

            return PersonajeViewHolder(binding.root)
        }
    }

}

class PersonajeComparator: DiffUtil.ItemCallback<Personaje>() {
    override fun areItemsTheSame(oldItem: Personaje, newItem: Personaje): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Personaje, newItem: Personaje): Boolean {
        return oldItem.id == newItem.id
    }

}
