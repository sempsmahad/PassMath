package com.kh69.passmath.extensions

import androidx.fragment.app.Fragment
import com.kh69.passmath.MathApp
import com.kh69.passmath.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as MathApp).questionRepository
    return ViewModelFactory(repository, this)
}