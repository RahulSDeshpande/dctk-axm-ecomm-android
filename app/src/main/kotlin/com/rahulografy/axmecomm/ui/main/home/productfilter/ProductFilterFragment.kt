package com.rahulografy.axmecomm.ui.main.home.productfilter

import androidx.lifecycle.Observer
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentProductFilterBinding
import com.rahulografy.axmecomm.ui.base.view.BaseDialogFragment
import com.rahulografy.axmecomm.ui.main.home.productfilter.adapter.ProductFilterAdapter
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.util.ext.toast

class ProductFilterFragment :
    BaseDialogFragment<FragmentProductFilterBinding, ProductFilterFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_product_filter

    override var viewModelClass = ProductFilterFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun initUi() {
        initProductFilterCategoryList(viewModel.getProductFilterCategoryItemList())
    }

    private fun initProductFilterCategoryList(
        listProductFilterCategoryItem: List<ProductFilterCategoryItem>?
    ) {
        if (!listProductFilterCategoryItem.isNullOrEmpty()) {
            viewDataBinding.apply {
                productFilterAdapter = ProductFilterAdapter(productFragmentViewModel = viewModel)
                executePendingBindings()
            }

            viewModel.listProductFilterCategoryItem.set(listProductFilterCategoryItem)
        }
    }

    override fun initSharedViewModelObservers() {
        viewModel
            .saveProductFilterEvent
            .observe(
                lifecycleOwner = this,
                observer = Observer { productItem ->
                    productItem?.let { status ->
                        toast(text = "FilterFragmentEvent status: $status")
                    }
                }
            )
    }
}