package com.rahulografy.axmecomm.di.module

import com.rahulografy.axmecomm.di.ActivityScoped
import com.rahulografy.axmecomm.ui.main.home.activity.HomeActivity
import com.rahulografy.axmecomm.ui.main.home.activity.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity
}