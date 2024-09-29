package com.example.data.base

import com.google.gson.annotations.SerializedName

open class BaseResponseDM {
    @field:SerializedName("status")
    val status: String? = null

    @field:SerializedName("code")
    val code: String? = null

    @field:SerializedName("message")
    val message: String? = null
}