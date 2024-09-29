package com.example.data.api.models.sources

import android.os.Parcelable
import com.example.domain.models.BaseResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesResponseDM(

    @field:SerializedName("sources")
    val sources: List<SourcesItemDM?>? = null,

    ) : BaseResponse(), Parcelable