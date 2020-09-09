package com.rahulografy.axmecomm.ui.main.home.fragment

import androidx.lifecycle.Observer
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentHomeBinding
import com.rahulografy.axmecomm.ui.base.view.BaseFragment
import com.rahulografy.axmecomm.ui.main.home.fragment.adapter.ProductAdapter
import com.rahulografy.axmecomm.util.ext.toast

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_home

    override var viewModelClass = HomeFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    private lateinit var productAdapter: ProductAdapter

    override fun initUi() {

        productAdapter = ProductAdapter(homeFragmentViewModel = viewModel)

        viewDataBinding.productAdapter = productAdapter
        viewDataBinding.executePendingBindings()

        viewModel.getProducts()

        viewModel
            .productClickEvent
            .observe(
                lifecycleOwner = this,
                observer = Observer { productItem ->
                    productItem?.let {
                        toast(text = "Phone: ${productItem.phone}")
                    }
                }
            )
    }
}