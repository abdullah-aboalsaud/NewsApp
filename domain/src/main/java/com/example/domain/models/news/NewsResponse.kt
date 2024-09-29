package com.example.domain.models.news

import android.os.Parcelable
import com.example.domain.models.BaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(


    val totalResults: Int? = null,

    val articles: List<Article?>? = null,

    ) : BaseResponse(), Parcelable