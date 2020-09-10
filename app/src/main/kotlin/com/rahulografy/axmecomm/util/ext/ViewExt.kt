package com.rahulografy.axmecomm.util.ext

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.rahulografy.axmecomm.R
import com.squareup.picasso.Picasso
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator

/**
 * Set up RecyclerView LIST with default configurations like:
 *
 * * VERTICAL ->
 *   - itemAnimator -> FadeInUpAnimator
 *   - layoutManager -> LinearLayoutManager.VERTICAL
 *
 * * HORIZONTAL ->
 *   - itemAnimator -> FadeInRightAnimator
 *   - layoutManager -> LinearLayoutManager.HORIZONTAL
 */
fun RecyclerView.list(isVertical: Boolean = true) {
    layoutManager =
        if (isVertical) {
            itemAnimator = FadeInUpAnimator()
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
 *   - itemAnimator -> FadeInUpAnimator
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
 * Set up RecyclerView STAGGERED GRID with default configurations like:
 *
 * * VERTICAL ->
 *   - itemAnimator -> FadeInUpAnimator
 *   - layoutManager -> LinearLayoutManager.VERTICAL
 *
 * * HORIZONTAL ->
 *   - itemAnimator -> FadeInRightAnimator
 *   - layoutManager -> LinearLayoutManager.HORIZONTAL
 */
fun RecyclerView.gridStaggered(isVertical: Boolean = true, spanCount: Int = 2) {
    layoutManager =
        if (isVertical) {
            itemAnimator = FadeInUpAnimator()
            StaggeredGridLayoutManager(
                spanCount,
                LinearLayoutManager.VERTICAL
            )
        } else {
            itemAnimator = FadeInRightAnimator()
            StaggeredGridLayoutManager(
                spanCount,
                LinearLayoutManager.HORIZONTAL
            )
        }
}

/**
 * Show [Toast] message
 */
fun Context.toast(text: String?, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

/**
 * Show [Toast] message
 */
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    context?.toast(text = text, duration = duration)
}

fun Context.alert(
    title: String,
    message: String,
    @DrawableRes icon: Int? = null,
    positiveButton: String = getString(R.string.ok)
): AlertDialog =
    AlertDialog
        .Builder(this)
        .setTitle(title)
        .setIcon(icon ?: android.R.drawable.screen_background_light_transparent)
        .setMessage(message)
        .setPositiveButton(
            positiveButton, null
        ).show()

fun ImageView?.picasso(
    url: String?,
    @DrawableRes placeHolderDrawableRes: Int = R.drawable.ic_placeholder_1
) = Picasso.get().load(url).placeholder(placeHolderDrawableRes).into(this)

fun ImageView?.picasso(@DrawableRes drawableRes: Int = R.drawable.ic_placeholder_1) =
    Picasso.get().load(drawableRes).into(this)

fun ImageView.glide(
    url: String?,
    @DrawableRes placeHolderDrawableRes: Int = R.drawable.ic_placeholder_1
) =
    Glide
        .with(context)
        .load(url)
        .into(this)