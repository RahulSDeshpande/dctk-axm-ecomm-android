package com.rahulografy.axmecomm.util

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.annotation.FloatRange

object Memory {

    fun calculateCacheSize(context: Context, @FloatRange(from = 0.01, to = 1.0) size: Float): Long {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val isLargeHeap = context.applicationInfo.flags and ApplicationInfo.FLAG_LARGE_HEAP != 0
        val memoryClass = activityManager.run { if (isLargeHeap) largeMemoryClass else memoryClass }
        return (memoryClass * 1024L * 1024L * size).toLong()
    }
}