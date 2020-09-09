package com.rahulografy.axmecomm.di.module

import android.app.Application
import android.content.Context
import com.rahulografy.axmecomm.AxmEcommApp
import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @ApplicationScoped
    @ApplicationContext
    abstract fun bindApplicationContext(application: AxmEcommApp): Context

    @Binds
    @ApplicationScoped
    abstract fun bindApplication(application: AxmEcommApp): Application
}