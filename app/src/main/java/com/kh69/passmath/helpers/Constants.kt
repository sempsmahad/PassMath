package com.kh69.passmath.helpers

import android.os.Looper


fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()
