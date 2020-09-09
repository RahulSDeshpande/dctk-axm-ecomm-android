package com.rahulografy.axmecomm.di.component

import com.rahulografy.axmecomm.AxmEcommApp
import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScoped
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<AxmEcommApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: AxmEcommApp): Builder

        fun build(): AppComponent
    }
}