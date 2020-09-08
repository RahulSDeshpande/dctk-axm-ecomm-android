package com.rahulografy.axmecomm.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rahulografy.axmecomm.di.ActivityScoped
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@ActivityScoped
abstract class BaseFragment<VDB : ViewDataBinding, BVM : BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewDataBinding: VDB

    protected abstract val viewModelClass: Class<BVM>

    protected lateinit var viewModel: BVM

    abstract val bindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidSupportInjection.inject(this)

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil
                .inflate(
                    inflater,
                    layoutRes,
                    container,
                    false
                )
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.apply {
            setVariable(
                bindingVariable,
                viewModel
            )
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
        }

        viewModel.start()

        initUi()
    }

    abstract fun initUi()

    override fun onDestroyView() {
        viewModel.stop()
        super.onDestroyView()
    }
}