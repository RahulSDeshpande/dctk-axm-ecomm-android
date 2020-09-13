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
import com.rahulografy.axmecomm.util.ext.show
import com.rahulografy.axmecomm.util.ext.toast
import kotlinx.android.synthetic.main.fragment_product.*

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

        initProductList(viewModel.getProductItemListByCategory(productCategory))
    }

    private fun initProductList(productItemList: List<ProductItem>?) {
        if (productItemList.isNullOrEmpty().not()) {
            viewDataBinding.apply {
                productAdapter = ProductAdapter(productFragmentViewModel = viewModel)
                executePendingBindings()
            }

            viewModel.products.set(Products(productItemList))

            showProductList(show = true)
        } else {
            showProductList(show = false)
        }
    }

    override fun initSharedViewModelObservers() {
        viewModel.apply {
            productClickEvent
                .observe(
                    lifecycleOwner = this@ProductFragment,
                    observer = Observer { productItem ->
                        productItem?.let {
                            toast(text = "Phone: ${productItem.phone}")
                        }
                    }
                )
        }
    }

    private fun showProductList(show: Boolean) {
        rvProducts.show(show = show)
        layoutNoData.show(show = show.not())
    }
}