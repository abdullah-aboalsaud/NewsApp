package com.example.newsapp.utils

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
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

fun Fragment.hideBottomNav() {
    requireActivity().findViewById<CardView>(R.id.cv_bottom_nav_container)
        .visibility = View.GONE
}

fun Fragment.showBottomNav() {
    requireActivity().findViewById<CardView>(R.id.cv_bottom_nav_container)
        .visibility = View.VISIBLE
}