package com.example.data.api.models.headlines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDM(
    val name: String? = null,
    val id: String? = null
) : Parcelable
