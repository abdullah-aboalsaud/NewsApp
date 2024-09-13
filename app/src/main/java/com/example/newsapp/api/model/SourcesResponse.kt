package com.example.newsapp.api.model

import android.os.Parcelable
import com.example.newsapp.base.BaseResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem?>? = null,


    ) : BaseResponse(), Parcelable