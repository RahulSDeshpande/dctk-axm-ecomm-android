package com.rahulografy.axmecomm.ui.main.home.productfilter

import androidx.lifecycle.Observer
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentProductBinding
import com.rahulografy.axmecomm.ui.base.view.BaseDialogFragment
import com.rahulografy.axmecomm.ui.main.home.product.adapter.ProductAdapter
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.Products
import com.rahulografy.axmecomm.util.ext.toast

class ProductFilterFragment :
    BaseDialogFragment<FragmentProductBinding, ProductFilterFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_product_filter

    override var viewModelClass = ProductFilterFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun initUi() {}

    private fun initProductList(listProductItem: List<ProductItem>?) {

        if (listProductItem.isNullOrEmpty().not()) {
            viewDataBinding.apply {
                productAdapter = ProductAdapter(productFragmentViewModel = viewModel)
                executePendingBindings()
            }

            viewModel.products.set(Products(listProductItem))
        }
    }

    override fun initSharedViewModelObservers() {
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