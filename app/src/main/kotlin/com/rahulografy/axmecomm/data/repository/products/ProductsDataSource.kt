package com.rahulografy.axmecomm.data.repository.products

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import io.reactivex.Flowable

interface ProductsDataSource {

    fun getProducts(): Flowable<ProductsResponse>
}