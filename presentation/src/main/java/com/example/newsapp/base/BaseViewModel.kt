package com.example.newsapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.BaseResponse
import com.example.newsapp.R
import com.example.newsapp.utils.fromJson
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {
    val uiMessage = MutableLiveData<UiMessage>()

    fun <T> handelError(response: Response<T>) {
        val errorResponse =
            response.errorBody()?.string()?.fromJson(BaseResponse::class.java)
        uiMessage.value = UiMessage(
            showLoading = false,
            showMessage = true,
            message = errorResponse?.message ?: "something went wrong",
            posBtnId = R.string.ok
        )
    }

    fun handelError(throwable: Throwable, posActionCallback: (() -> Unit)? = null) {
        if (throwable is HttpException) {
            val errorResponse =
                throwable.response()?.errorBody()?.string()?.fromJson(BaseResponse::class.java)
            uiMessage.value = UiMessage(
                showLoading = false,
                message = errorResponse?.message,
                showMessage = true,
                posBtnId = R.string.ok,
                onPositiveClick = posActionCallback
            )
            return
        }

        val messageId = when (throwable) {
            is UnknownHostException,
            is IOException -> {
                R.string.connection_error
            }

            else -> {
                R.string.something_went_wrong
            }
        }
        uiMessage.value = UiMessage(
            showLoading = false,
            messageId = messageId,
            showMessage = true,
            posBtnId = R.string.ok,
            onPositiveClick = posActionCallback
        )

    }

    fun hideLoading() {
        uiMessage.value = UiMessage(showLoading = false)
    }

    fun showLoading(message: String? = null, messageId: Int? = null) {
        uiMessage.value = UiMessage(
            showLoading = true,
            message = message,
            messageId = messageId
        )
    }
}