package com.rahulografy.axmecomm.ui.main.home.fragment

import androidx.databinding.ObservableBoolean
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.product.manager.ProductManager
import com.rahulografy.axmecomm.ui.main.home.productfilter.manager.ProductFilterManager
import javax.inject.Inject

class HomeFragmentViewModel
@Inject constructor(
    val productManager: ProductManager,
    val productFilterManager: ProductFilterManager
) : BaseViewModel() {

    val isDataProcessing = ObservableBoolean(false)

    fun getProducts(forceApi: Boolean = false) {
        isDataProcessing.set(true)
        productManager.getProducts(forceApi = forceApi)
    }
}