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
import com.rahulografy.axmecomm.util.ext.toArrayList
import com.rahulografy.axmecomm.util.ext.toHashMap
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

    private var products: Products? = null

    private var mapCategoryWiseProductItemListBackup: HashMap<String, List<ProductItem>>? = null

    var mapCategoryWiseProductItemListFinal = SingleLiveEvent<HashMap<String, List<ProductItem>>?>()

    fun getProducts(forceApi: Boolean = false) {

        if (forceApi || products?.products.isNullOrEmpty()) {
            addDisposable(
                disposable = productsRepository
                    .getProducts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        onProductsReceived(it)
                    }, { error ->
                        products = null
                        error.printStackTrace()
                    })
            )
        } else {
            mapCategoryWiseProductItemListFinal.value = mapCategoryWiseProductItemListFinal.value
        }
    }

    private fun onProductsReceived(productsResponse: ProductsResponse?) {
        products = productMapper.map(input = productsResponse)

        mapCategoryWiseProductItemListBackup =
            productMapper.groupByCategory(products?.products)?.toHashMap()

        mapCategoryWiseProductItemListFinal.value =
            productMapper.groupByCategory(products?.products)?.toHashMap()

        productFilterManager.init(products?.products)
    }

    fun getProductItemList(category: String?) =
        mapCategoryWiseProductItemListFinal.value?.get(category)

    fun updateProductItemListFinalBasedOnFilter() {
        productFilterManager.productFilterCategoryItemListFinal

        val mapCategoryWiseProductItemListFinalNew =
            mapCategoryWiseProductItemListFinal.value.toHashMap()

        mapCategoryWiseProductItemListBackup?.forEach {

            var productItemListFinalNew =
                mapCategoryWiseProductItemListFinalNew[it.key].toArrayList()

            productItemListFinalNew.clear()

            productItemListFinalNew = updateProductItemListBackupToFinal(
                productItemListBackup = it.value.toArrayList(),
                productItemListFinal = productItemListFinalNew
            )

            mapCategoryWiseProductItemListFinalNew[it.key] = productItemListFinalNew
        }

        mapCategoryWiseProductItemListFinal.value = mapCategoryWiseProductItemListFinalNew
    }

    private fun updateProductItemListBackupToFinal(
        productItemListBackup: ArrayList<ProductItem>?,
        productItemListFinal: ArrayList<ProductItem>
    ): ArrayList<ProductItem> {

        if (productItemListBackup.isNullOrEmpty()) {
            return arrayListOf()
        }

        productItemListBackup.forEach {
            val productFilterValueMapFinal =
                productFilterManager.productFilterCategoryWiseProductFilterValueMapFinal

            val phoneList = productFilterValueMapFinal["phone"]
            val priceList = productFilterValueMapFinal["price"]
            val simList = productFilterValueMapFinal["sim"]
            val gpsList = productFilterValueMapFinal["gps"]
            val audioJackList = productFilterValueMapFinal["audioJack"]

            val ignoreFilter = true

            if (((phoneList.isNullOrEmpty() && ignoreFilter) || phoneList!!.contains(it.phone))
                && ((priceList.isNullOrEmpty() && ignoreFilter) || priceList!!.contains(it.priceEurInt.toString()))
                && ((simList.isNullOrEmpty() && ignoreFilter) || simList!!.contains(it.sim))
                && ((gpsList.isNullOrEmpty() && ignoreFilter) || gpsList!!.contains(it.gps))
                && ((audioJackList.isNullOrEmpty() && ignoreFilter) || audioJackList!!.contains(it.audioJack))
            ) {
                productItemListFinal.add(it)
            }
        }

        return productItemListFinal
    }
}