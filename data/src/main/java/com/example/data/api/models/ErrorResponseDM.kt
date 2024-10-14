package com.example.data.api.models

import com.google.gson.annotations.SerializedName

data class ErrorResponseDM(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
