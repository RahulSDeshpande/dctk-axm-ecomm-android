package com.rahulografy.axmecomm.di.module

import com.rahulografy.axmecomm.data.remote.products.datasource.ProductsRemoteDataSource
import com.rahulografy.axmecomm.data.repository.products.ProductsDataSource
import com.rahulografy.axmecomm.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    @RemoteData
    abstract fun bindProductsRemoteDataSource(
        productsRemoteDataSource: ProductsRemoteDataSource
    ): ProductsDataSource
}