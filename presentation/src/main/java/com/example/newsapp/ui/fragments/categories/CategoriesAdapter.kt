package com.example.newsapp.ui.fragments.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newsapp.databinding.ItemCategoryBinding
import com.example.newsapp.ui.models.CategoryModel

class CategoriesAdapter(var categoryList: MutableList<CategoryModel>? = null) :
    Adapter<CategoriesAdapter.CategoryViewHolder>() {
    var onItemClick: OnItemClick? = null

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryModel?) {
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

    fun submitList(categoryList: MutableList<CategoryModel>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun interface OnItemClick {
        fun onclick(categoryTitle: String)
    }


}