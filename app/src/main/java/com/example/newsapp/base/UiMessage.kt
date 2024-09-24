package com.example.newsapp.base


data class UiMessage(
    val showLoading: Boolean? = null,
    val showMessage: Boolean? = null,
    val message: String? = null,
    val messageId: Int? = null,

    val positiveBtnText: String = "OK",
    val posBtnId: Int? = null,
    val negativeBtnText: String? = null,
    val negBtnId: Int? = null,
    val onPositiveClick: (() -> Unit)? = null,
    val onNegativeClick: (() -> Unit)? = null,

    val isCancelable: Boolean = true,
    val exception: Throwable? = null
)

fun interface OnDialogClick {
    fun onclick()
}

