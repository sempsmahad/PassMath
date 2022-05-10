package com.kh69.passmath.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.kh69.passmath.MathApp
import com.kh69.passmath.ViewModelFactory
import com.kh69.passmath.helpers.isOnMainThread
import com.kh69.passmath.ui.SettingsActivity

fun Activity.launchSettings() {
    hideKeyboard()
    startActivity(Intent(applicationContext, SettingsActivity::class.java))
}

fun Activity.hideKeyboard() {
    if (isOnMainThread()) {
        hideKeyboardSync()
    } else {
        Handler(Looper.getMainLooper()).post {
            hideKeyboardSync()
        }
    }
}

fun Activity.hideKeyboardSync() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
    window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    currentFocus?.clearFocus()
}

fun Activity.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as MathApp).questionRepository
    return ViewModelFactory(repository, it)
}