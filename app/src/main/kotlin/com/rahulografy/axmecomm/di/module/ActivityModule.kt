package com.rahulografy.axmecomm.di.module

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.rahulografy.axmecomm.di.ActivityScoped
import com.rahulografy.axmecomm.ui.base.view.BaseActivity
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScoped
        fun provideFragmentManager(activity: AppCompatActivity): FragmentManager =
            activity.supportFragmentManager
    }

    @Binds
    @ActivityScoped
    abstract fun bindAppCompatActivity(activity: BaseActivity<ViewDataBinding, BaseViewModel>): AppCompatActivity

    @Binds
    @ActivityScoped
    abstract fun bindActivity(activity: AppCompatActivity): Activity
}