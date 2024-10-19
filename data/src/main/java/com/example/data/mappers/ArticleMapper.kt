package com.example.data.mappers

import com.example.data.api.models.news.ArticleDM
import com.example.domain.models.news.Article

open class ArticleMapper() {

    companion object {
        fun toArticle(articleDM: ArticleDM?): Article {
            return Article(
                publishedAt = articleDM?.publishedAt,
                author = articleDM?.author,
                urlToImage = articleDM?.urlToImage,
                description = articleDM?.description,
                source = toSource(articleDM?.source),
                title = articleDM?.title,
                url = articleDM?.url,
                content = articleDM?.content
            )
        }

        fun toArticleList(articlesListDM: List<ArticleDM?>?): List<Article>? {
            return articlesListDM?.map { articleDM ->
                toArticle(articleDM)
            }
        }
    }

}
