package com.rahulografy.axmecomm.ui.main.home.product.mapper

import com.rahulografy.axmecomm.data.remote.products.model.ProductResponse
import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.ui.base.mapper.BaseMapper
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.Products
import com.rahulografy.axmecomm.util.ext.amountize
import javax.inject.Inject

class ProductMapper
@Inject constructor() : BaseMapper<ProductsResponse, Products> {

    override fun map(input: ProductsResponse?) =
        Products(products = input?.map { it.mapProductItem() })

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

    fun groupByCategory(productItemList: List<ProductItem>?) =
        productItemList?.groupBy { it.brand.toString() }
}