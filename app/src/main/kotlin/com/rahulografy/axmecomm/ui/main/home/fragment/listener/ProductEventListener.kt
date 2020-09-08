package com.rahulografy.axmecomm.ui.main.home.fragment.listener

import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetResponse

// TODO | NEED A BaseEventListener WHICH SHOULD CONTAIN ALL COMMON CALLBACKS
interface ProductEventListener {

    fun onItemClicked(mobileHandsetResponse: MobileHandsetResponse)

    fun onItemAddToWishList(mobileHandsetResponse: MobileHandsetResponse) {}

    fun onItemAddToCart(mobileHandsetResponse: MobileHandsetResponse) {}

    fun onItemRemoveFromCart(mobileHandsetResponse: MobileHandsetResponse) {}
}