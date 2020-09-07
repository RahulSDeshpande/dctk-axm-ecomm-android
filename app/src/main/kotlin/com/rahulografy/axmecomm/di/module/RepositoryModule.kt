package com.rahulografy.axmecomm.di.module

import com.rahulografy.axmecomm.data.remote.mobilehandsets.datasource.MobileHandsetsRemoteDataSource
import com.rahulografy.axmecomm.data.repository.mobilehandsets.MobileHandsetsDataSource
import com.rahulografy.axmecomm.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    @RemoteData
    abstract fun bindMobileHandsetsRemoteDataSource(
        mobileHandsetsRemoteDataSource: MobileHandsetsRemoteDataSource
    ): MobileHandsetsDataSource
}