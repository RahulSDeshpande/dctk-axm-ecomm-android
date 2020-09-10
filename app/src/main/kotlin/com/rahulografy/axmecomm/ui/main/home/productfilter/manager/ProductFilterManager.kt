package com.rahulografy.axmecomm.ui.main.home.productfilter.manager

import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.mapper.ProductFilterMapper
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.util.SingleLiveEvent
import javax.inject.Inject

@ApplicationScoped
class ProductFilterManager
@Inject constructor(
    private val productFilterMapper: ProductFilterMapper
) : BaseViewModel() {

    /*
    var mapCategoryWiseProductFilterItems =
        SingleLiveEvent<LinkedHashMap<String, List<ProductFilterItem>>?>()

    fun init(listProductItem: List<ProductItem>) {
        mapCategoryWiseProductFilterItems.value = productFilterMapper.map(input = listProductItem)
    }

    fun getProductFiltersForId(id: Int) =
        mapCategoryWiseProductFilterItems.value?.values?.toList()?.get(id)

    fun getProductFiltersForCategory(category: String) =
        mapCategoryWiseProductFilterItems.value?.get(category)
    */

    var listProductFilterCategoryItem = SingleLiveEvent<List<ProductFilterCategoryItem>?>()

    fun init(listProductItem: List<ProductItem>?) {
        listProductFilterCategoryItem.value = productFilterMapper.map(input = listProductItem)
    }

    fun getProductFilterItemsForId(id: Int) =
        listProductFilterCategoryItem.value
            ?.get(id)
            ?.listProductFilterItem
            ?: listOf()

    fun getProductFilterItemsForCategory(category: String) =
        listProductFilterCategoryItem.value
            ?.find { it.value == category }
            ?.listProductFilterItem
            ?: listOf()

    fun getSelectedProductFilterCategoryItems() =
        getSelectedProductFilterCategoryItems(isSelected = true)

    fun getUnselectedProductFilterCategoryItems() =
        getSelectedProductFilterCategoryItems(isSelected = false)

    private fun getSelectedProductFilterCategoryItems(isSelected: Boolean) =
        listProductFilterCategoryItem.value?.map { productFilterCategoryItem ->
            ProductFilterCategoryItem(
                value = productFilterCategoryItem.value,
                title = productFilterCategoryItem.title,
                listProductFilterItem = productFilterCategoryItem.listProductFilterItem
                    .filter { productFilterItem ->
                        productFilterItem.isSelected == isSelected
                    }
            )
        }

    fun updateProductFilterForId(id: Int, value: String, isSelected: Boolean) {
        listProductFilterCategoryItem.value
            ?.get(id)
            ?.listProductFilterItem
            ?.get(id)
            ?.isSelected = isSelected
    }

    fun updateProductFilterForCategory(category: String, value: String, isSelected: Boolean) {
        listProductFilterCategoryItem.value
            ?.find { it.value == category }
            ?.listProductFilterItem
            ?.find { it.value == value }
            ?.isSelected = isSelected
    }
}