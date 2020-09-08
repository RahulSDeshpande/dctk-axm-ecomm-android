package com.rahulografy.axmecomm.ui.component.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.axmecomm.ui.main.home.fragment.adapter.ProductAdapter
import com.rahulografy.axmecomm.ui.main.home.fragment.model.ProductItem
import com.rahulografy.axmecomm.util.ext.grid

object RecyclerViewBindingAdapter {

    @BindingAdapter("app:isVerticalItemDecoration")
    @JvmStatic
    fun RecyclerView.isVerticalItemDecoration(isVertical: Boolean) {
        addItemDecoration(
            DividerItemDecoration(
                context,
                if (isVertical) VERTICAL else HORIZONTAL
            )
        )
    }

    @BindingAdapter("app:adapter")
    @JvmStatic
    fun RecyclerView?.setAdapter(
        productAdapter: ProductAdapter
    ) {
        this?.apply {
            grid()
            adapter = productAdapter
        }
    }

    @BindingAdapter("app:items")
    @JvmStatic
    fun RecyclerView?.setProducts(
        items: List<ProductItem>
    ) {
        with(this?.adapter as ProductAdapter?) {
            this?.setData(items)
        }
    }
}