package com.example.data.api.models.news

import android.os.Parcelable
import com.example.data.api.models.headlines.SourceDM
import com.example.data.mappers.ArticleMapper.Companion.toArticle
import com.example.domain.models.news.Article
import kotlinx.parcelize.Parcelize


@Parcelize
data class ArticleDM(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: SourceDM? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null

) : Parcelable{
    fun toArticleList(articlesListDM: List<ArticleDM?>?): List<Article>? {
        return articlesListDM?.map { articleDM ->
            toArticle(articleDM)
        }
    }
}