package com.rahulografy.axmecomm.data.repository.products

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.di.qualifier.RemoteData
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class ProductsRepository @Inject constructor(
    @RemoteData private val productsDataSource: ProductsDataSource
) : ProductsDataSource {

    private var cachedProductsResponse: ProductsResponse? = null

    override fun getProducts(): Single<ProductsResponse> =
        productsDataSource.getProducts()
}