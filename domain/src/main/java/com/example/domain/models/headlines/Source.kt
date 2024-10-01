package com.example.domain.models.headlines

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val name: String? = null,
    val id: String? = null,
    val color: Int? = null
) : Parcelable
