package com.example.newsapp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.api.model.news.ArticlesItem
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(private var newsList: MutableList<ArticlesItem?> = mutableListOf()) :
    Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = newsList[position]
        article?.let { holder.bind(it) }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun changeNewsList(articles: List<ArticlesItem?>?) {
        newsList = articles?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem) {
            Glide.with(itemView)
                .load(article.urlToImage)
                .placeholder(R.drawable.progress_animation)
                .into(binding.ivNews)
            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
            binding.tvDate.text = article.formatDate()

        }

    }


}