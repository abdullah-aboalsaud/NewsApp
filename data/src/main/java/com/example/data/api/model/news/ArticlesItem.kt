package com.example.data.api.model.news

import android.os.Parcelable
import com.example.data.api.model.sources.SourcesItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Locale

@Parcelize
data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: SourcesItem? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) : Parcelable {

    fun formatDate(): String? {
        try {
            val receivedDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val date = receivedDateFormat.parse(publishedAt)
            val simpleDateFormat = SimpleDateFormat("yyyy MM dd", Locale.ENGLISH)
            return simpleDateFormat.format(date)
        } catch (ex: Exception) {
            return publishedAt
        }


    }
}