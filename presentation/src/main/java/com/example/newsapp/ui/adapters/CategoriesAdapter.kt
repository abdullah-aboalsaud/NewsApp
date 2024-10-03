package com.example.newsapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemCategoryBinding
import com.example.newsapp.ui.models.CategoryModel
import com.example.newsapp.R

class CategoriesAdapter(var categoryList: MutableList<CategoryModel>? = null) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    var onItemClick: OnItemClick? = null

    private var selectedPosition = 0 // To track the selected item

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val primaryRedColor =ContextCompat.getColor(binding.root.context,R.color.primary_red)
        val whiteColor =ContextCompat.getColor(binding.root.context,R.color.white)
        val blackColor =ContextCompat.getColor(binding.root.context,R.color.black)

        fun bind(category: CategoryModel?,isSelected: Boolean) {

            if(isSelected){
                binding.categoryContainer.setBackgroundColor(primaryRedColor)
                binding.tvTitle.setTextColor(whiteColor)
            }else{
                binding.categoryContainer.setBackgroundColor(whiteColor)
                binding.tvTitle.setTextColor(blackColor)
            }

            // bind data
            binding.tvTitle.text = category?.title


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val category = categoryList?.get(position)
        holder.bind(category,position==selectedPosition)
        holder.itemView.setOnClickListener {
            onItemClick?.onclick(category?.title ?: "")

            if (position != selectedPosition) {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition) // Refresh previous selected item
                notifyItemChanged(position) // Refresh new selected item
            }
        }
    }

    fun submitList(categoryList: MutableList<CategoryModel>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun interface OnItemClick {
        fun onclick(categoryTitle: String)
    }


}