package com.rahulografy.axmecomm.data.repository.products

import com.rahulografy.axmecomm.data.remote.products.datasource.ProductsRemoteDataSource
import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.di.qualifier.RemoteData
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepository @Inject constructor(
    @RemoteData private val productsRemoteDataSource: ProductsRemoteDataSource
) : ProductsDataSource {

    private var cachedProductsResponse: ProductsResponse? = null

    override fun getProducts(): Flowable<ProductsResponse> =
        productsRemoteDataSource
            .getProducts()
            .doOnNext {
                cachedProductsResponse = it
            }
}