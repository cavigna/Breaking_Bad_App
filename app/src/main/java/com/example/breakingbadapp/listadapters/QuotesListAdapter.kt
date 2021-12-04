package com.example.breakingbadapp.listadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.databinding.ItemRowQuoteBinding
import com.example.breakingbadapp.model.Quotes

class QuotesListAdapter: ListAdapter<Quotes, QuotesViewHolder>(QuotesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quote = getItem(position)

        with(holder.binding){
            tvQuoteRow.text = """ "${quote.quote}" """
            tvNameRow.text = quote.author
        }
    }
}

class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemRowQuoteBinding.bind(itemView)

    companion object{
        fun create(parent: ViewGroup): QuotesViewHolder{
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowQuoteBinding.inflate(layoutInflaterB, parent, false)
            return QuotesViewHolder(binding.root)
        }
    }

}

class QuotesComparator : DiffUtil.ItemCallback<Quotes>(){
    override fun areItemsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
        return oldItem.id == newItem.id
    }

}
