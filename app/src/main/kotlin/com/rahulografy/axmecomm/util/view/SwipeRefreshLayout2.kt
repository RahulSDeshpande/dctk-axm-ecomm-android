package com.rahulografy.axmecomm.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

/**
 * Custom version of SwipeRefreshLayout which disables itself in case of any child views are
 * scrolled horizontally & enables back when the scrolling is done
 */
class SwipeRefreshLayout2(
    context: Context,
    attrs: AttributeSet?
) : SwipeRefreshLayout(context, attrs) {

    private val touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var previousX = 0f
    private var declined = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                previousX = MotionEvent.obtain(event).x
                // New touch event
                declined = false
            }

            MotionEvent.ACTION_MOVE -> {
                val xDiff = abs(event.x - previousX)
                if (declined || xDiff > touchSlop) {
                    // Memorize this touch event & ignore all the upcoming events
                    declined = true
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}