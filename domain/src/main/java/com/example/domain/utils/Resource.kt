package com.example.domain.utils

sealed interface Resource<T> {
    data class Success<T>(val data: T, val message: String? = null) : Resource<T>
    data class Failure<T>(val throwable: Throwable) : Resource<T>

}