package com.rahulografy.axmecomm.data.remote.products.service

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.util.Constants.Network.Api.URL_GET_PRODUCTS
import io.reactivex.Single
import retrofit2.http.GET

interface ProductsRemoteService {

    @GET(URL_GET_PRODUCTS)
    fun getProducts(): Single<ProductsResponse>
}