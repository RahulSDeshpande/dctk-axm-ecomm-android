package com.rahulografy.axmecomm.ui.component.bindingadapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun AppCompatImageView?.setImageUrl(url: String?) {
    if (this != null && url.isNullOrBlank().not()) {
        Glide
            .with(context)
            .load(url)
            .into(this)
    }
}