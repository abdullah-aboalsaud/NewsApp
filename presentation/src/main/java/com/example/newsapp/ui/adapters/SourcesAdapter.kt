package com.example.newsapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.domain.models.headlines.Source
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemSourceBinding


class SourcesAdapter(private var sourcesList: MutableList<Source>? = mutableListOf()) :
    Adapter<SourcesAdapter.ViewHolder>() {
    var onItemClick: OnItemClick? = null

    private var selectedPosition = -1 // To track the selected item

    class ViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {
        val primaryRedColor =ContextCompat.getColor(binding.root.context,R.color.primary_red)
        val whiteColor =ContextCompat.getColor(binding.root.context,R.color.white)
        val blackColor =ContextCompat.getColor(binding.root.context,R.color.black)

        fun bind(source: Source,isSelected: Boolean) {

            if(isSelected){
                binding.sourceContainer.setBackgroundColor(primaryRedColor)
                binding.tvTitle.setTextColor(whiteColor)
            }else{
                binding.sourceContainer.setBackgroundColor(whiteColor)
                binding.tvTitle.setTextColor(blackColor)
            }
            // bind data
            binding.tvTitle.text = source.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemSourceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val source = sourcesList?.get(position)
        source?.let { holder.bind(it,position==selectedPosition) }
        holder.itemView.setOnClickListener {
            source?.let { source -> onItemClick?.onclick(source) }

            if (position != selectedPosition) {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition) // Refresh previous selected item
                notifyItemChanged(position) // Refresh new selected item
            }
        }

    }

    override fun getItemCount(): Int {
        return sourcesList?.size ?: 0
    }

    fun submitList(sources: List<Source>?) {
        sourcesList = sources?.toMutableList() ?: mutableListOf()
        selectedPosition = -1 // Reset selection when submitting a new list
        notifyDataSetChanged()
    }


    fun interface OnItemClick {
        fun onclick(source: Source)
    }


}