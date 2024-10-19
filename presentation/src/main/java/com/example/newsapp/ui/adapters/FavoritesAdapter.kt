package com.example.newsapp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.news.Article
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding

class FavoritesAdapter(private var newsList: MutableList<Article?> = mutableListOf()) :
    RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    var onItemClick : ((Article)-> Unit) ?=null
    var onLongItemClick: ((Article)->Unit) ?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val article = newsList[position]
        article?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            article?.let { article ->
                onItemClick?.invoke(article)
            }
        }

        holder.itemView.setOnLongClickListener{
            article?.let { article ->
                onLongItemClick?.invoke(article)
                true
            }?:false
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun submitList(articles: List<Article?>?) {
        newsList = articles?.toMutableList() ?: mutableListOf()
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            Glide.with(itemView)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivNews)

            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
            binding.tvDate.text = article.formatDate()
        }

    }


}