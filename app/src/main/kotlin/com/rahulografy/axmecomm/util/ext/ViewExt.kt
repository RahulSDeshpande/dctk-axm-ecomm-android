package com.rahulografy.axmecomm.util.ext

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator

/**
 * Set up RecyclerView LIST with default configurations like:
 *
 * * VERTICAL ->
 *   - itemAnimator -> FadeInDownAnimator
 *   - layoutManager -> LinearLayoutManager.VERTICAL
 *
 * * HORIZONTAL ->
 *   - itemAnimator -> FadeInRightAnimator
 *   - layoutManager -> LinearLayoutManager.HORIZONTAL
 */
fun RecyclerView.setup(isVertical: Boolean = true) {
    layoutManager =
        if (isVertical) {
            itemAnimator = FadeInDownAnimator()
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        } else {
            itemAnimator = FadeInRightAnimator()
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
}

/**
 * Set up RecyclerView GRID with default configurations like:
 *
 * * VERTICAL ->
 *   - itemAnimator -> FadeInDownAnimator
 *   - layoutManager -> LinearLayoutManager.VERTICAL
 *
 * * HORIZONTAL ->
 *   - itemAnimator -> FadeInRightAnimator
 *   - layoutManager -> LinearLayoutManager.HORIZONTAL
 */
fun RecyclerView.grid(isVertical: Boolean = true, spanCount: Int = 2) {
    layoutManager =
        if (isVertical) {
            itemAnimator = FadeInUpAnimator()
            GridLayoutManager(
                context,
                spanCount,
                LinearLayoutManager.VERTICAL,
                false
            )
        } else {
            itemAnimator = FadeInRightAnimator()
            GridLayoutManager(
                context,
                spanCount,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
}

/**
 * Show [Toast] message
 */
fun Context.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

/**
 * Show [Toast] message
 */
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    context?.toast(text = text, duration = duration)
}