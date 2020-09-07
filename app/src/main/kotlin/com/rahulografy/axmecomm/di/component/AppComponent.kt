package com.rahulografy.axmecomm.di.component

import com.rahulografy.axmecomm.AxmEcommApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<AxmEcommApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: AxmEcommApp): Builder

        fun build(): AppComponent
    }
}