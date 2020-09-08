package com.rahulografy.axmecomm.ui.main.home.fragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetResponse
import com.rahulografy.axmecomm.databinding.ItemProductType1Binding
import com.rahulografy.axmecomm.ui.main.home.fragment.listener.ProductEventListener

class ProductViewHolder(private val binding: ItemProductType1Binding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        mobileHandsetResponse: MobileHandsetResponse,
        productEventListener: ProductEventListener
    ) {
        with(receiver = binding)
        {
            this.mobileHandset = mobileHandsetResponse
            this.mobileHandsetEventListener = productEventListener
            executePendingBindings()
        }
    }
}