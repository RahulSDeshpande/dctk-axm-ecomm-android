package com.rahulografy.axmecomm.data.remote.mobilehandsets.datasource

import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.data.remote.mobilehandsets.service.MobileHandsetsRemoteService
import com.rahulografy.axmecomm.data.repository.mobilehandsets.MobileHandsetsDataSource
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MobileHandsetsRemoteDataSource @Inject constructor(
    private val mobileHandsetsRemoteService: MobileHandsetsRemoteService
) : MobileHandsetsDataSource {

    override fun getMobileHandsets(): Flowable<MobileHandsetsResponse> =
        mobileHandsetsRemoteService
            .getMobileHandsets()
            .flatMap {
                Flowable
                    .fromIterable(it)
                    .flatMap {
                        mobileHandsetsRemoteService.getMobileHandsets()
                    }
            }
}