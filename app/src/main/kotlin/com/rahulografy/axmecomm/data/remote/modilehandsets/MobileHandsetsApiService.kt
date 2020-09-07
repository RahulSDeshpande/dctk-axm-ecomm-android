package com.rahulografy.axmecomm.data.remote.modilehandsets

import com.rahulografy.axmecomm.data.remote.modilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.util.Constants
import io.reactivex.Flowable
import retrofit2.http.GET

interface MobileHandsetsApiService {

    @GET(Constants.Api.URL_GET_MOBILE_HANDSETS)
    fun getMobileHandsets(): Flowable<MobileHandsetsResponse>
}