package com.example.newsapp.ui.models

import android.os.Parcelable
import com.example.domain.models.news.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesList(
    val articles: List<Article>?
): Parcelable