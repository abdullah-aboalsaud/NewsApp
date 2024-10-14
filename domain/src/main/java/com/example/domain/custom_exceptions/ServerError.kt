package com.example.domain.custom_exceptions


class ServerError(
    val status: String? = null,
    val code: String? = null,
    val serverMessage: String? = "something went wrong"

) : Throwable(serverMessage)
