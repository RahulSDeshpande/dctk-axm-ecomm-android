package com.rahulografy.axmecomm.data.remote.products.service

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.util.Constants
import io.reactivex.Flowable
import retrofit2.http.GET

interface ProductsRemoteService {

    @GET(Constants.Api.URL_GET_PRODUCTS)
    fun getProducts(): Flowable<ProductsResponse>
}