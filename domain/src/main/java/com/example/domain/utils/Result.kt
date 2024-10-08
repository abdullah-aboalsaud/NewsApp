package com.example.domain.utils

 sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: String) : Result<T>(message = message)
    class Loading<T> : Result<T>()
    class Unspecified<T> : Result<T>()
}