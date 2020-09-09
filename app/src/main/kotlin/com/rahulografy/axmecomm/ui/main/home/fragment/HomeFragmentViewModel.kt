package com.rahulografy.axmecomm.ui.main.home.fragment

import androidx.databinding.ObservableBoolean
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.ProductsManager
import javax.inject.Inject

class HomeFragmentViewModel
@Inject constructor(
    val productsManager: ProductsManager
) : BaseViewModel() {

    val isApiCallInProgress = ObservableBoolean(false)

    fun getProducts(forceApi: Boolean = false) {
        isApiCallInProgress.set(true)
        productsManager.getProducts(forceApi = forceApi)
    }
}