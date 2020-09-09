package com.rahulografy.axmecomm.ui.main.home.product

import androidx.databinding.ObservableField
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.ProductsManager
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.Products
import com.rahulografy.axmecomm.util.SingleLiveEvent
import javax.inject.Inject

class ProductFragmentViewModel
@Inject constructor(
    private val productsManager: ProductsManager
) : BaseViewModel() {

    val products = ObservableField<Products>()

    val productClickEvent = SingleLiveEvent<ProductItem>()

    fun getProductsFiltered(brand: String?) =
        productsManager.getProductsFiltered(brand = brand)

    fun openProductDetails(productItem: ProductItem) {
        productClickEvent.value = productItem
    }
}