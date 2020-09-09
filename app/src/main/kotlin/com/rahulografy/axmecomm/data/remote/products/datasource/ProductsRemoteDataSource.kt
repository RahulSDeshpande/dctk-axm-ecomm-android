package com.rahulografy.axmecomm.data.remote.products.datasource

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.data.remote.products.service.ProductsRemoteService
import com.rahulografy.axmecomm.data.repository.products.ProductsDataSource
import com.rahulografy.axmecomm.di.ApplicationScoped
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScoped
class ProductsRemoteDataSource @Inject constructor(
    private val productsRemoteService: ProductsRemoteService
) : ProductsDataSource {

    override fun getProducts(): Single<ProductsResponse> = productsRemoteService.getProducts()
}