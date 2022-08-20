package com.kh69.passmath.legacy.helpers

import android.os.Looper


fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()
