package com.rahulografy.axmecomm.data.repository.products

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import io.reactivex.Single

interface ProductsDataSource {

    fun getProducts(): Single<ProductsResponse>
}