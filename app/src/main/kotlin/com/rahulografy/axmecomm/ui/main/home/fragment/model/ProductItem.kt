package com.rahulografy.axmecomm.ui.main.home.fragment.model

import com.rahulografy.axmecomm.ui.base.model.BaseProductItem

data class ProductItem(
    val announceDate: Int? = null,
    val audioJack: String? = null,
    val battery: String? = null,
    val brand: String? = null,
    val gps: String? = null,
    val id: Int? = null,
    val phone: String? = null,
    val picture: String? = null,
    val priceEurInt: Int? = null,
    val priceEurString: String? = null,
    val resolution: String? = null,
    val sim: String? = null
) : BaseProductItem()

/**
 * Returns object of [ProductItem] converted from [BaseProductItem]
 */
fun BaseProductItem.toProductItem() = this as ProductItem