package com.kh69.passmath

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
//  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//    return creator() as T
//  }
//}

//inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
//  return if (creator == null)
//    ViewModelProvider(this).get(T::class.java)
//  else
//    ViewModelProvider(this, ViewModelFactory(creator))[T::class.java]
//}