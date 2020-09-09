package com.rahulografy.axmecomm.ui.main.home

import com.rahulografy.axmecomm.data.remote.products.model.ProductsResponse
import com.rahulografy.axmecomm.data.repository.products.ProductsRepository
import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.product.mapper.ProductsMapper
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.Products
import com.rahulografy.axmecomm.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ApplicationScoped
class ProductsManager
@Inject constructor(
    private val productsRepository: ProductsRepository,
    private val productsMapper: ProductsMapper
) : BaseViewModel() {

    private var products = SingleLiveEvent<Products?>()

    var mapCategoryWiseProducts = SingleLiveEvent<Map<String, List<ProductItem>>?>()

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
            products.reset()
        }
    }

    private fun onProductsReceived(productsResponse: ProductsResponse?) {
        products.value = productsMapper.map(input = productsResponse)

        mapCategoryWiseProducts.value = products.value?.products?.groupBy { it.brand.toString() }
    }

    fun getProductsFiltered(brand: String?) =
        mapCategoryWiseProducts.value?.get(brand)
}