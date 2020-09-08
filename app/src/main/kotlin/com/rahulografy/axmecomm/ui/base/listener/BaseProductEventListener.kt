package com.rahulografy.axmecomm.ui.base.listener

import com.rahulografy.axmecomm.ui.base.model.BaseProductItem

interface BaseProductEventListener : BaseEventListener {

    fun onItemAddToWishListClicked(item: BaseProductItem) {}

    fun onItemAddToCartClicked(item: BaseProductItem) {}

    fun onItemRemoveFromCart(item: BaseProductItem) {}
}