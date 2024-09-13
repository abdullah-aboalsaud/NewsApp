package com.example.newsapp.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson


fun Context.showDialog(
    message: String,
    positiveBtnText: String = "OK",
    negativeBtnText: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    isCancelable: Boolean = true
) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
    builder.setPositiveButton(positiveBtnText) { dialog, i ->
        onPositiveClick?.invoke()
        dialog.dismiss()
    }

    negativeBtnText?.let {
        builder.setNegativeButton(it) { _, _ ->
            onNegativeClick?.invoke()
        }
    }

    builder.setCancelable(isCancelable)

    builder.create().show()
}

fun <T> String.fromJson(className: Class<T>): T {
    return Gson().fromJson(this, className)
}
