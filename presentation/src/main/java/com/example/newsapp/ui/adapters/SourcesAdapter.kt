package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.domain.models.headlines.Source
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemSourceBinding


class SourcesAdapter(private var sourcesList: MutableList<Source>? = mutableListOf()) :
    Adapter<SourcesAdapter.ViewHolder>() {
    var onItemClick: OnItemClick? = null

    class ViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.tvTitle.text = source.name
            binding.constraintContainer.setBackgroundResource(source.color ?: R.color.primary_red)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemSourceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = sourcesList?.get(position)
        source?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            source?.let { source -> onItemClick?.onclick(source) }
        }

    }

    override fun getItemCount(): Int {
        return sourcesList?.size ?: 0
    }

    fun submitList(sources: List<Source>?) {
        sourcesList = sources?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }


    fun interface OnItemClick {
        fun onclick(source: Source)
    }


}