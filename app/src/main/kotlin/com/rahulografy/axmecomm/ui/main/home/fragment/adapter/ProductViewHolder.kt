package com.rahulografy.axmecomm.ui.main.home.fragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.axmecomm.databinding.ItemProductType1Binding
import com.rahulografy.axmecomm.ui.main.home.fragment.listener.ProductEventListener
import com.rahulografy.axmecomm.ui.main.home.fragment.model.ProductItem

class ProductViewHolder(private val binding: ItemProductType1Binding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        productItem: ProductItem,
        productEventListener: ProductEventListener
    ) {
        with(receiver = binding)
        {
            this.productItem = productItem
            this.productEventListener = productEventListener
            executePendingBindings()
        }
    }
}