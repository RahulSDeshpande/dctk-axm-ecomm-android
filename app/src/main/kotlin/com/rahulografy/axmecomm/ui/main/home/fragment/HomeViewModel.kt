package com.rahulografy.axmecomm.ui.main.home.fragment

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rahulografy.axmecomm.data.repository.products.ProductsRepository
import com.rahulografy.axmecomm.ui.base.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.fragment.mapper.ProductsMapper
import com.rahulografy.axmecomm.ui.main.home.fragment.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.fragment.model.Products
import com.rahulografy.axmecomm.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val productsMapper: ProductsMapper
) : BaseViewModel() {

    val isApiCallInProgress = ObservableBoolean(false)

    val products = ObservableField<Products>()

    val onProductClickedEvent = SingleLiveEvent<ProductItem>()

    private var disposable: Disposable? = null

    override fun start() {
        getProducts()
    }

    override fun stop() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun refresh() {
        getProducts()
    }

    private fun getProducts() {
        disposable =
            productsRepository
                .getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isApiCallInProgress.set(true)
                }
                .doAfterTerminate {
                    isApiCallInProgress.set(false)
                }
                .subscribe({
                    products.set(productsMapper.map(it))
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun openProductDetails(productItem: ProductItem) {
        onProductClickedEvent.value = productItem
    }
}