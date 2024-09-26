package com.example.newsapp.ui.fragments.categories.all_categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newsapp.databinding.ItemCategoryBinding

class CategoriesAdapter(var categoryList: MutableList<ModelCategory>? = null) :
    Adapter<CategoriesAdapter.CategoryViewHolder>() {
    var onItemClick: OnItemClick? = null

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: ModelCategory?) {
            binding.categoryContainer.setBackgroundResource(category!!.color)
            binding.tvTitle.text = category.title
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

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList?.get(position)
        holder.bind(category)
        holder.itemView.setOnClickListener {
            onItemClick?.onclick(category?.title ?: "")
        }
    }

    fun submitList(categoryList: MutableList<ModelCategory>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun interface OnItemClick {
        fun onclick(title: String)
    }


}