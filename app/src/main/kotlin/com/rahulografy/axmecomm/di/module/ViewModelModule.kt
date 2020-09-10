package com.rahulografy.axmecomm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.di.ViewModelKey
import com.rahulografy.axmecomm.ui.main.home.activity.HomeActivityViewModel
import com.rahulografy.axmecomm.ui.main.home.fragment.HomeFragmentViewModel
import com.rahulografy.axmecomm.ui.main.home.product.ProductFragmentViewModel
import com.rahulografy.axmecomm.ui.main.home.productfilter.ProductFilterFragmentViewModel
import com.rahulografy.axmecomm.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        AppModule::class,
        RepositoryModule::class
    ]
)
abstract class ViewModelModule {

    @ApplicationScoped
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    abstract fun bindHomeActivityViewModel(viewModel: HomeActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductFragmentViewModel::class)
    abstract fun bindProductFragmentViewModel(viewModel: ProductFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductFilterFragmentViewModel::class)
    abstract fun bindProductFilterFragmentViewModel(viewModel: ProductFilterFragmentViewModel): ViewModel
}