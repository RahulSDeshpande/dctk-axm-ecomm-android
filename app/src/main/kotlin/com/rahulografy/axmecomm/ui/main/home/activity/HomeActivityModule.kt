package com.rahulografy.axmecomm.ui.main.home.activity

import com.rahulografy.axmecomm.di.FragmentScoped
import com.rahulografy.axmecomm.ui.main.home.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}