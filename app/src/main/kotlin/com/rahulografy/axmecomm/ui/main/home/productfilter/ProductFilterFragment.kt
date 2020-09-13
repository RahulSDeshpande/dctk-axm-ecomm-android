package com.rahulografy.axmecomm.ui.main.home.productfilter

import android.content.DialogInterface
import androidx.lifecycle.Observer
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentProductFilterBinding
import com.rahulografy.axmecomm.ui.base.view.BaseDialogFragment
import com.rahulografy.axmecomm.ui.main.home.productfilter.adapter.ProductFilterAdapter
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem

class ProductFilterFragment :
    BaseDialogFragment<FragmentProductFilterBinding, ProductFilterFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_product_filter

    override var viewModelClass = ProductFilterFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun initUi() {
        initProductFilterCategoryList(viewModel.getProductFilterCategoryItemList())
    }

    private fun initProductFilterCategoryList(
        productFilterCategoryItemList: List<ProductFilterCategoryItem>?
    ) {
        if (!productFilterCategoryItemList.isNullOrEmpty()) {
            viewDataBinding.apply {
                productFilterAdapter = ProductFilterAdapter(productFragmentViewModel = viewModel)
                executePendingBindings()
            }

            viewModel.productFilterCategoryItemList.set(productFilterCategoryItemList)
        }
    }

    override fun initSharedViewModelObservers() {
        viewModel.apply {

            productFilterSaveEvent
                .observe(
                    lifecycleOwner = this@ProductFilterFragment,
                    observer = Observer { dismissDialog() }
                )

            productFilterCancelEvent
                .observe(
                    lifecycleOwner = this@ProductFilterFragment,
                    observer = Observer { dismissDialog() }
                )


            productFilterResetEvent
                .observe(
                    lifecycleOwner = this@ProductFilterFragment,
                    observer = Observer { dismissDialog() }
                )
        }
    }

    private fun dismissDialog() {
        dismissAllowingStateLoss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.cancelProductFilter()
    }
}