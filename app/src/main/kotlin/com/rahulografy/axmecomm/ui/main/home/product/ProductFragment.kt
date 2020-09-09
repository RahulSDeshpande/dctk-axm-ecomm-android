package com.rahulografy.axmecomm.ui.main.home.product

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentProductBinding
import com.rahulografy.axmecomm.ui.base.view.BaseFragment
import com.rahulografy.axmecomm.ui.main.home.product.adapter.ProductAdapter
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.Products
import com.rahulografy.axmecomm.util.ext.toast

class ProductFragment : BaseFragment<FragmentProductBinding, ProductFragmentViewModel>() {

    companion object {
        private const val ARG_PRODUCT_CATEGORY = "PRODUCT_CATEGORY"

        fun initProductFragment(productCategory: String): ProductFragment {
            val productFragment = ProductFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PRODUCT_CATEGORY, productCategory)
            productFragment.arguments = bundle
            return productFragment
        }
    }

    override val layoutRes get() = R.layout.fragment_product

    override var viewModelClass = ProductFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun initUi() {

        val productCategory = arguments?.getString(ARG_PRODUCT_CATEGORY)

        initProductList(viewModel.getProductsFiltered(productCategory))
    }

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