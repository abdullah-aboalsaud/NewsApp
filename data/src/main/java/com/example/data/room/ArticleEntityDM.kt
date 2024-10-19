package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.api.models.headlines.SourceDM

@Entity
data class ArticleEntityDM(

    val id: Int?=null,
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: SourceDM? = null,
    val title: String? = null,
    @PrimaryKey()
    val url: String = "",
    val content: String? = null
)
