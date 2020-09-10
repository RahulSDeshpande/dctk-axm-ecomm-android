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

    val listProductFilterCategoryItem = ObservableField<List<ProductFilterCategoryItem>>()

    val saveProductFilterEvent = SingleLiveEvent<Boolean>()

    fun getProductFilterCategoryItemList() =
        productFilterManager.listProductFilterCategoryItem.value

    fun saveProductFilter(save: Boolean) {
        saveProductFilterEvent.value = save
    }
}