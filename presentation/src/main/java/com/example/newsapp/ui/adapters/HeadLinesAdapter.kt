package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.domain.models.news.Article
import com.example.newsapp.databinding.ItemHeadlineBinding

class HeadLinesAdapter(var articlesList: MutableList<Article?>? = null) :
    Adapter<HeadLinesAdapter.CategoryViewHolder>() {
    var onItemClick: OnItemClick? = null

    class CategoryViewHolder(private val binding: ItemHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article?) {
            binding.tvTitle.text = article?.title
            binding.tvAuthor.text = article?.author
            binding.tvDescription.text = article?.description
            Glide.with(itemView)
                .load(article?.urlToImage)
                .into(binding.ivArticle)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemHeadlineBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articlesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val article = articlesList?.get(position)
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClick?.onclick(article)
        }
    }

    fun changeNewsList(articles: List<Article?>?) {
        articlesList = articles?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }


    fun interface OnItemClick {
        fun onclick(article: Article?)
    }


}