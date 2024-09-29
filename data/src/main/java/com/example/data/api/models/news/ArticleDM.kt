package com.example.data.api.models.news

import android.os.Parcelable
import com.example.data.api.models.headlines.SourceDM
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

) : Parcelable