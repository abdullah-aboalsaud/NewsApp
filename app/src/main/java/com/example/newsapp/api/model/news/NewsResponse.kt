package com.example.newsapp.api.model.news

import android.os.Parcelable
import com.example.newsapp.base.BaseResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,

    ) : BaseResponse(), Parcelable