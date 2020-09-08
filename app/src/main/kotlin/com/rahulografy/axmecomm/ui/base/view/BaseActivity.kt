package com.rahulografy.axmecomm.ui.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel> :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewDataBinding: VDB

    protected abstract val viewModelClass: Class<BVM>

    protected lateinit var viewModel: BVM

    abstract val bindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[viewModelClass]

        viewDataBinding = DataBindingUtil.setContentView(this, layoutRes)

        viewDataBinding.apply {
            setVariable(
                bindingVariable,
                viewModel
            )
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }

        viewModel.start()

        initUi()
    }

    abstract fun initUi()

    override fun onDestroy() {
        viewModel.stop()
        super.onDestroy()
    }
}