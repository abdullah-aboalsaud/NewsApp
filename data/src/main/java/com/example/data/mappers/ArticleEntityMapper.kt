package com.example.data.mappers

import com.example.data.api.models.headlines.SourceDM
import com.example.data.room.ArticleEntityDM
import com.example.domain.models.headlines.Source
import com.example.domain.models.news.Article

// ArticleEntity to Domain
fun ArticleEntityDM.toDomain(): Article {
    return Article(
        id = this.id,
        title = this.title,
        content = this.content,
        publishedAt = this.publishedAt,
        author = this.author,
        urlToImage = this.urlToImage,
        description = this.description,
        url = this.url,
        source = this.source?.toDomain()
    )
}

fun SourceDM.toDomain(): Source {
    return Source(
        id = this.id,
        name = this.name
    )
}


fun Article.toEntityDM(): ArticleEntityDM {
    return ArticleEntityDM(
        title = this.title,
        content = this.content,
        source = this.source?.toEntityDM(),
        publishedAt = this.publishedAt,
        author = this.author,
        urlToImage = this.urlToImage,
        description = this.description,
        url = this.url.toString(),
    )
}

// Source to SourceDM
fun Source.toEntityDM(): SourceDM {
    return SourceDM(
        id = this.id,
        name = this.name
    )
}
