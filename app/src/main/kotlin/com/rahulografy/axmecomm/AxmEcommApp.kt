package com.rahulografy.axmecomm

import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class AxmEcommApp : DaggerApplication() {

    private lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun onCreate() {
        super.onCreate()

        // Init Stetho
        Stetho.initializeWithDefaults(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    public override fun applicationInjector() = androidInjector
}