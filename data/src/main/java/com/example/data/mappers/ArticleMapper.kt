package com.example.data.mappers


import com.example.data.api.models.news.ArticleDM
import com.example.domain.models.news.Article


fun toArticle(articleDM: ArticleDM?): Article {
    return Article(
        articleDM?.publishedAt,
        articleDM?.author,
        articleDM?.urlToImage,
        articleDM?.description,
        toSource(articleDM?.source),
        articleDM?.title,
        articleDM?.url,
        articleDM?.content
    )
}

fun toArticleList(articlesListDM: List<ArticleDM?>?): List<Article>? {
    return articlesListDM?.map { articleDM ->
        toArticle(articleDM)
    }
}

