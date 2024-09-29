package com.example.data.api.models.news

import android.os.Parcelable
import com.example.domain.models.BaseResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponseDM(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticleDM?>? = null,

    ) : BaseResponse(), Parcelable