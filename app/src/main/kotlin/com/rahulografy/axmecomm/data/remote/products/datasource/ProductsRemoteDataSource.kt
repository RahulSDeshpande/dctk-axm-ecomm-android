package com.rahulografy.axmecomm.data.remote.products.datasource

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.data.remote.products.service.ProductsRemoteService
import com.rahulografy.axmecomm.data.repository.products.ProductsDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRemoteDataSource @Inject constructor(
    private val productsRemoteService: ProductsRemoteService
) : ProductsDataSource {

    override fun getProducts(): Flowable<ProductsResponse> =
        productsRemoteService
            .getProducts()
            .flatMap {
                Flowable
                    .fromIterable(it)
                    .flatMap {
                        productsRemoteService.getProducts()
                    }
            }
}