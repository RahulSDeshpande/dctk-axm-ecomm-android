package com.rahulografy.axmecomm.data.repository.mobilehandsets

import com.rahulografy.axmecomm.data.remote.mobilehandsets.datasource.MobileHandsetsRemoteDataSource
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.di.qualifier.RemoteData
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MobileHandsetsRepository @Inject constructor(
    @RemoteData private val mobileHandsetsRemoteDataSource: MobileHandsetsRemoteDataSource
) : MobileHandsetsDataSource {

    private var cachedMobileHandsetsResponse: MobileHandsetsResponse? = null

    override fun getMobileHandsets(): Flowable<MobileHandsetsResponse> =
        mobileHandsetsRemoteDataSource
            .getMobileHandsets()
            .doOnNext {
                cachedMobileHandsetsResponse = it
            }
}