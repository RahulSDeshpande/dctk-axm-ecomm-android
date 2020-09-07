package com.rahulografy.axmecomm.data.remote.mobilehandsets.service

import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.util.Constants
import io.reactivex.Flowable
import retrofit2.http.GET

interface MobileHandsetsRemoteService {

    @GET(Constants.Api.URL_GET_MOBILE_HANDSETS)
    fun getMobileHandsets(): Flowable<MobileHandsetsResponse>
}