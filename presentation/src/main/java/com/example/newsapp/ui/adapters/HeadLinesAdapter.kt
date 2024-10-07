package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.domain.models.news.Article
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemHeadlineBinding

class HeadLinesAdapter(private var articlesList: MutableList<Article?> = mutableListOf()) :
    Adapter<HeadLinesAdapter.ViewHolder>() {

    var onItemClick: ( (Article) -> Unit )? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemHeadlineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articlesList[position]
        article?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            article?.let { article ->
                onItemClick?.invoke(article)
            }
        }

    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    fun submitList(articles: List<Article?>?) {
        articlesList = articles?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemHeadlineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {

            Glide.with(itemView)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivArticle)

            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
            binding.tvDescription.text = article.description

        }

    }


}