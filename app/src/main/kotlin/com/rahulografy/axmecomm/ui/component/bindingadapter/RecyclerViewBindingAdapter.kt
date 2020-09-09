package com.rahulografy.axmecomm.ui.component.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.axmecomm.ui.main.home.product.adapter.ProductAdapter
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.util.ext.gridStaggered

@BindingAdapter("app:isVerticalItemDecoration")
fun RecyclerView.isVerticalItemDecoration(isVertical: Boolean) {
    addItemDecoration(
        DividerItemDecoration(
            context,
            if (isVertical) VERTICAL else HORIZONTAL
        )
    )
}

@BindingAdapter("app:adapter")
fun RecyclerView?.setAdapter(
    productAdapter: ProductAdapter?
) {
    this?.apply {
        gridStaggered()
        adapter = productAdapter
    }
}

@BindingAdapter("app:items")
fun RecyclerView?.setProducts(
    items: List<ProductItem>?
) {
    with(this?.adapter as ProductAdapter?) {
        this?.setData(items)
    }
}