package com.rahulografy.axmecomm.ui.main.home.productfilter.listener

import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem

interface ProductFilterEventListener {

    fun onProductFilterItemSelected(
        productFilterCategoryItem: ProductFilterCategoryItem,
        productCategoryItemId: Int,
        productCategoryItemValue: String,
        productItemId: Int,
        productItemValue: String,
        isSelected: Boolean
    )

    fun onProductFilterSaved()

    fun onProductFilterCancelled()

    fun onProductFilterReset()
}