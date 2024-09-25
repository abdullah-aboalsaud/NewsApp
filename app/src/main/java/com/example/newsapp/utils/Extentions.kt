package com.example.newsapp.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.newsapp.R
import com.google.gson.Gson


fun Context.showDialog(
    @StringRes messageId: Int? = null,
    message: String? = null,
    @StringRes posBtnTextId: Int = R.string.ok,
    @StringRes negBtnTextId: Int? = null,

    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    isCancelable: Boolean = true
) {
    val builder = AlertDialog.Builder(this)
    if (messageId != null) builder.setMessage(getString(messageId))
    message?.let { builder.setMessage(it) }
    builder.setPositiveButton(getString(posBtnTextId)) { dialog, i ->
        onPositiveClick?.invoke()
        dialog.dismiss()
    }
    if (negBtnTextId != null) {
        builder.setNegativeButton(getString(negBtnTextId)) { _, _ ->
            onNegativeClick?.invoke()
        }
    }

    builder.setCancelable(isCancelable)
    builder.create().show()
}

fun <T> String.fromJson(className: Class<T>): T {
    return Gson().fromJson(this, className)
}



