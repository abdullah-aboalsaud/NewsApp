package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.domain.models.news.Article
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemSeeAllBinding


class SeeAllAdapter(private var newsList: MutableList<Article?> = mutableListOf()) :
    Adapter<SeeAllAdapter.ViewHolder>() {

    var onItemClick: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemSeeAllBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = newsList[position]
        article?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            article?.let { article ->
                onItemClick?.invoke(article)
            }
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun submitList(articles: List<Article?>?) {
        newsList = articles?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemSeeAllBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            Glide.with(itemView)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivNewsBackground)

            binding.tvTitle.text = article.title
            binding.tvAuther.text = article.author
            binding.tvContent.text = article.content
        }

    }


}