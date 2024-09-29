package com.example.domain.models.sources

import android.os.Parcelable
import com.example.domain.models.BaseResponse
import com.example.domain.models.headlines.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesResponse(

    val sources: List<Source>? = null

) : BaseResponse(), Parcelable