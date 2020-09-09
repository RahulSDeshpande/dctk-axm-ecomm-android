package com.rahulografy.axmecomm.ui.component.bindingadapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.rahulografy.axmecomm.util.ext.picasso

@BindingAdapter("imageUrl")
fun AppCompatImageView?.setImageUrl(url: String?) {
    if (this != null && url.isNullOrBlank().not()) {
        picasso(url = url)
    }
}