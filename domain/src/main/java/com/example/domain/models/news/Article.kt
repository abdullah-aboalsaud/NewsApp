package com.example.domain.models.news

import android.os.Parcelable
import com.example.domain.models.headlines.Source
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Locale

@Parcelize
data class Article(
    val id:Int ?=null,
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
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