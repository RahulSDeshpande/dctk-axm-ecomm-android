package com.rahulografy.axmecomm.ui.component.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("app:isRefreshing")
fun SwipeRefreshLayout.isRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter("app:onRefreshListener")
fun SwipeRefreshLayout.setOnRefreshListener(function: () -> Unit) {
    setOnRefreshListener { function() }
}