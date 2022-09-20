package com.example.marvel.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.databinding.ListViewItemBinding
import com.example.marvel.network.Marvel

class MarvelListAdapter(val clickListener: MarvelListener) :
    ListAdapter<Marvel, MarvelListAdapter.MarvelViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Marvel>() {

        override fun areItemsTheSame(oldItem: Marvel, newItem: Marvel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Marvel, newItem: Marvel): Boolean {
            return oldItem.description == newItem.description && oldItem.thumbnail == newItem.thumbnail
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        val marvel = getItem(position)
        holder.bind(clickListener, marvel)
    }

    class MarvelViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: MarvelListener, marvel: Marvel) {
            binding.marvel = marvel
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

}

class MarvelListener(val clickListener: (marvel: Marvel) -> Unit) {
    fun onClick(marvel: Marvel) = clickListener(marvel)
}
