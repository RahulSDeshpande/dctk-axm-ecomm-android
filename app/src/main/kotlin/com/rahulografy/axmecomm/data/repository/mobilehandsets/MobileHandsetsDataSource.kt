package com.rahulografy.axmecomm.data.repository.mobilehandsets

import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import io.reactivex.Flowable

interface MobileHandsetsDataSource {

    fun getMobileHandsets(): Flowable<MobileHandsetsResponse>
}