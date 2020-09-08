package com.rahulografy.axmecomm.ui.main.home.fragment.mapper

import com.rahulografy.axmecomm.data.remote.products.model.ProductResponse
import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.ui.base.mapper.BaseMapper
import com.rahulografy.axmecomm.ui.main.home.fragment.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.fragment.model.Products
import com.rahulografy.axmecomm.util.ext.amountize
import javax.inject.Inject

class ProductsMapper
@Inject constructor() : BaseMapper<ProductsResponse, Products> {

    override fun map(input: ProductsResponse): Products {
        return Products(products = input.map { it.mapProductItem() })
    }

    private fun ProductResponse.mapProductItem() =
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
            priceEurString = priceEur.amountize(),
            resolution = resolution,
            sim = sim
        )
}