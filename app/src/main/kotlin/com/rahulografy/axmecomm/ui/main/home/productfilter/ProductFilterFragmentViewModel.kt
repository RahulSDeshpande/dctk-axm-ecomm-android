package com.rahulografy.axmecomm.ui.main.home.productfilter

import androidx.databinding.ObservableField
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.productfilter.manager.ProductFilterManager
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.util.SingleLiveEvent
import javax.inject.Inject

class ProductFilterFragmentViewModel
@Inject constructor(
    private val productFilterManager: ProductFilterManager
) : BaseViewModel() {

    val productFilterCategoryItemList = ObservableField<List<ProductFilterCategoryItem>>()

    val productFilterSaveEvent = SingleLiveEvent<Boolean>()
    val productFilterCancelEvent = SingleLiveEvent<Boolean>()
    val productFilterResetEvent = SingleLiveEvent<Boolean>()

    fun getProductFilterCategoryItemList() = productFilterManager.productFilterCategoryItemListFinal

    fun onProductFilterItemSelected(
        productFilterCategoryItem: ProductFilterCategoryItem,
        productCategoryItemId: Int,
        productCategoryItemValue: String,
        productItemId: Int,
        productItemValue: String,
        isSelected: Boolean
    ) {
        productFilterManager.onProductFilterItemSelected(
            productCategoryItemId = productCategoryItemId,
            productCategoryItemValue = productCategoryItemValue,
            productItemId = productItemId,
            productItemValue = productItemValue,
            productFilterCategoryItem = productFilterCategoryItem,
            isSelected = isSelected
        )
    }

    fun saveProductFilter() {
        productFilterManager.updateProductFilterCategoryItemListTempToFinal()
        productFilterSaveEvent.value = true
    }

    fun cancelProductFilter() {
        productFilterManager.resetProductFilterCategoryItemListTemp()
        productFilterCancelEvent.value = true
    }

    fun resetProductFilter() {
        productFilterManager.resetProductFilterCategoryItemListTempAndFinal()
        productFilterResetEvent.value = true
    }
}