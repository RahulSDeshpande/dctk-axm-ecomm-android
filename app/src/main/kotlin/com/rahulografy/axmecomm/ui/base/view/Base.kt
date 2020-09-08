package com.rahulografy.axmecomm.ui.base.view

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

// TODO | WIP
abstract class Base<BVM : BaseViewModel, VDB : ViewDataBinding> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewDataBinding: VDB

    protected abstract val viewModelClass: Class<BVM>

    protected lateinit var viewModel: BVM

    abstract val bindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutRes: Int
}