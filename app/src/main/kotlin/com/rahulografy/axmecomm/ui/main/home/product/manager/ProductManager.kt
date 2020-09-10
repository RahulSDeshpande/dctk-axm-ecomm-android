package com.rahulografy.axmecomm.ui.main.home.product.manager

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.data.repository.products.ProductsRepository
import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.product.mapper.ProductMapper
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.Products
import com.rahulografy.axmecomm.ui.main.home.productfilter.manager.ProductFilterManager
import com.rahulografy.axmecomm.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScoped
class ProductManager
@Inject constructor(
    private val productsRepository: ProductsRepository,
    private val productMapper: ProductMapper,
    private val productFilterManager: ProductFilterManager
) : BaseViewModel() {

    private var products = SingleLiveEvent<Products?>()

    var mapCategoryWiseProductItems = SingleLiveEvent<Map<String, List<ProductItem>>?>()

    fun getProducts(forceApi: Boolean = false) {

        if (forceApi || products.value?.products.isNullOrEmpty()) {
            addDisposable(
                disposable = productsRepository
                    .getProducts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onProductsReceived(it)
                    }, { error ->
                        products.reset()
                        error.printStackTrace()
                    })
            )
        } else {
            mapCategoryWiseProductItems.value = mapCategoryWiseProductItems.value
        }
    }

    private fun onProductsReceived(productsResponse: ProductsResponse?) {
        products.value = productMapper.map(input = productsResponse)

        mapCategoryWiseProductItems.value = productMapper.groupByCategory(products.value?.products)

        productFilterManager.init(products.value?.products)
    }

    fun getProductsByCategory(category: String?) =
        mapCategoryWiseProductItems.value?.get(category)
}