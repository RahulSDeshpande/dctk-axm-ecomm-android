package com.rahulografy.axmecomm.ui.main.home.fragment.mapper

import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetResponse
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.ui.base.BaseMapper
import com.rahulografy.axmecomm.ui.main.home.fragment.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.fragment.model.Products
import com.rahulografy.axmecomm.util.ext.formatAmount
import javax.inject.Inject

class InfoCardNotesMapper
@Inject constructor() : BaseMapper<MobileHandsetsResponse, Products> {

    override fun map(input: MobileHandsetsResponse): Products {
        return Products(products = input.map { it.mapProductItem() })
    }

    private fun MobileHandsetResponse.mapProductItem() =
        ProductItem(
            announceDate = announceDate,
            audioJack = audioJack,
            battery = battery,
            brand = brand,
            gps = gps,
            id = id,
            phone = phone,
            picture = picture,
            priceEurInt = priceEur,
            priceEurString = priceEur.formatAmount(),
            resolution = resolution,
            sim = sim
        )
}