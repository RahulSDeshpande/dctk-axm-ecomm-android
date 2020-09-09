package com.rahulografy.axmecomm.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rahulografy.axmecomm.data.remote.products.service.ProductsRemoteService
import com.rahulografy.axmecomm.util.Constants.Network.Api.HEADER_SECRET_KEY_KEY
import com.rahulografy.axmecomm.util.Constants.Network.Api.HEADER_SECRET_KEY_VALUE
import com.rahulografy.axmecomm.util.Constants.Network.Api.URL_BASE
import com.rahulografy.axmecomm.util.Constants.Network.Cache.NAME
import com.rahulografy.axmecomm.util.Constants.Network.Timeout.CONNECTION
import com.rahulografy.axmecomm.util.Constants.Network.Timeout.READ
import com.rahulografy.axmecomm.util.Constants.Network.Timeout.WRITE
import com.rahulografy.axmecomm.util.Memory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private fun buildOkHttpClient(application: Application): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(getHeaderInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .connectTimeout(CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(WRITE, TimeUnit.SECONDS)
            .readTimeout(READ, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(application.cacheDir, NAME),
                    Memory.calculateCacheSize(context = application, size = .25f)
                )
            )
            .build()

    private fun getHeaderInterceptor() =
        Interceptor { chain ->
            val requestBuilder =
                chain.request()
                    .newBuilder()
                    .addHeader(
                        HEADER_SECRET_KEY_KEY,
                        HEADER_SECRET_KEY_VALUE
                    )

            chain.proceed(requestBuilder.build())
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application) = buildOkHttpClient(application)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideProductsRemoteService(retrofit: Retrofit): ProductsRemoteService =
        retrofit.create(ProductsRemoteService::class.java)
}