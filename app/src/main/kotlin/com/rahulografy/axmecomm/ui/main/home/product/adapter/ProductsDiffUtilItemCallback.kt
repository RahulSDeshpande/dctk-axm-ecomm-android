package com.rahulografy.axmecomm.ui.main.home.product.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem

class ProductsDiffUtilItemCallback : DiffUtil.ItemCallback<ProductItem>() {

    override fun areItemsTheSame(
        oldItem: ProductItem,
        newItem: ProductItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ProductItem,
        newItem: ProductItem
    ) = oldItem.id == newItem.id
}/**/