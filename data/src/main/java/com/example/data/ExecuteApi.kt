package com.example.data

import com.example.data.api.models.ErrorResponseDM
import com.example.domain.custom_exceptions.ConnectionError
import com.example.domain.custom_exceptions.ServerError
import com.example.domain.utils.Resource
import com.google.gson.Gson
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException


suspend fun <T> executeApi(api: suspend () -> T): Resource<T> {

    return try {
        Resource.Success(api.invoke())
    } catch (ex: Exception) {

        when (ex) {
            is HttpException -> {
                val errorBody = ex.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ErrorResponseDM::class.java)
                Resource.Failure(
                  throw  ServerError(
                        errorResponse.status,
                        errorResponse.code,
                        errorResponse.message
                    )
                )
            }

            is SocketTimeoutException -> {
                Resource.Failure(throw ConnectionError("Request timed out, please try again."))
            }

            is IOException,
            is TimeoutException -> {
                Resource.Failure(throw ConnectionError("Network error, please check your internet connection."))
            }

            else -> {
                Resource.Failure(throw ServerError("An unexpected error occurred: ${ex.localizedMessage}"))
            }
        }

    }

}