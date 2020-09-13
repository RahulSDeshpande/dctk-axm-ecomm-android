package com.rahulografy.axmecomm.ui.main.home.productfilter.manager

import com.rahulografy.axmecomm.di.ApplicationScoped
import com.rahulografy.axmecomm.ui.base.view.BaseViewModel
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.mapper.ProductFilterMapper
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterItem
import com.rahulografy.axmecomm.util.SingleLiveEvent
import com.rahulografy.axmecomm.util.ext.toArrayList
import javax.inject.Inject

@ApplicationScoped
class ProductFilterManager
@Inject constructor(
    private val productFilterMapper: ProductFilterMapper
) : BaseViewModel() {

    var productFilterCategoryItemListFinalChangeEvent = SingleLiveEvent<Boolean>()

    var productFilterCategoryItemListFinal = arrayListOf<ProductFilterCategoryItem>()

    var productFilterCategoryWiseProductFilterValueMapFinal = hashMapOf<String, ArrayList<String>>()

    private var productFilterCategoryItemListTemp = arrayListOf<ProductFilterCategoryItem>()

    fun init(productItemList: List<ProductItem>?) {
        productFilterCategoryItemListFinal =
            productFilterMapper.map(input = productItemList) ?: arrayListOf()
    }

    fun getProductFilterItemsForId(id: Int) =
        productFilterCategoryItemListFinal[id].productFilterItemList

    fun getProductFilterItemsForCategory(category: String) =
        productFilterCategoryItemListFinal
            .find { it.value == category }
            ?.productFilterItemList
            ?: listOf()

    fun getSelectedProductFilterCategoryItemList() =
        getSelectedProductFilterCategoryItemList(isSelected = true)

    fun getUnselectedProductFilterCategoryItemList() =
        getSelectedProductFilterCategoryItemList(isSelected = false)

    private fun getSelectedProductFilterCategoryItemList(isSelected: Boolean) =
        productFilterCategoryItemListFinal
            .map { productFilterCategoryItem ->
                ProductFilterCategoryItem(
                    id = productFilterCategoryItem.id,
                    value = productFilterCategoryItem.value,
                    title = productFilterCategoryItem.title,
                    productFilterItemList = productFilterCategoryItem
                        .productFilterItemList
                        .filter { productFilterItem ->
                            productFilterItem.isSelected == isSelected
                        },
                    isMandatory = productFilterCategoryItem.isMandatory,
                    productFilterType = productFilterCategoryItem.productFilterType
                )
            }

    // SAVE FILTER

    private fun updateProductFilterForId(id: Int, value: String, isSelected: Boolean) {
        productFilterCategoryItemListFinal[id]
            .productFilterItemList[id]
            .isSelected = isSelected
    }

    private fun updateProductFilterForCategory(
        category: String,
        value: String,
        isSelected: Boolean
    ) {
        productFilterCategoryItemListFinal
            .find { it.value == category }
            ?.productFilterItemList
            ?.find { it.value == value }
            ?.isSelected = isSelected
    }

    // TODO | MOVE THIS TO ProductFilterMapper
    fun onProductFilterItemSelected(
        productFilterCategoryItem: ProductFilterCategoryItem,
        productCategoryItemId: Int,
        productCategoryItemValue: String,
        productItemId: Int,
        productItemValue: String,
        isSelected: Boolean
    ) {
        val productFilterCategoryItemOld =
            productFilterCategoryItemListTemp.find { it.value == productCategoryItemValue }

        if (productFilterCategoryItemOld == null) {

            // Double check whether the filter is selected
            if (isSelected) {

                productFilterCategoryItem.apply {

                    val productFilterItemListNew =
                        getProductFilterItemListNew(
                            productFilterItemList = productFilterItemList,
                            productItemValue = productItemValue,
                            isSelected = isSelected
                        )

                    productFilterCategoryItemListTemp.add(
                        ProductFilterCategoryItem(
                            id = id,
                            value = value,
                            title = title,
                            productFilterItemList = productFilterItemListNew,
                            isMandatory = isMandatory,
                            productFilterType = productFilterType
                        )
                    )
                }
            }
        } else {
            if (isSelected) {
                productFilterCategoryItemOld
                    .productFilterItemList
                    .find { it.value == productItemValue }
                    ?.isSelected = isSelected
            } else {
                productFilterCategoryItemOld
                    .productFilterItemList
                    .apply {
                        toArrayList()
                            .remove(
                                find {
                                    it.value == productItemValue
                                }
                            )
                    }
            }
        }
    }

    private fun getProductFilterItemListNew(
        productFilterItemList: List<ProductFilterItem>,
        productItemValue: String,
        isSelected: Boolean
    ): ArrayList<ProductFilterItem> {

        val productFilterItemListNew = arrayListOf<ProductFilterItem>()

        productFilterItemList.forEach {

            productFilterItemListNew.add(

                ProductFilterItem(
                    id = it.id,
                    value = it.value,
                    isSelected = if (it.value == productItemValue) {
                        isSelected
                    } else {
                        it.isSelected
                    }
                )
            )
        }

        return productFilterItemListNew
    }

    fun updateProductFilterCategoryItemListTempToFinal() {
        productFilterMapper.saveProductFilterCategoryItemListTempToFinal(
            productFilterCategoryItemListTemp,
            productFilterCategoryItemListFinal,
            productFilterCategoryWiseProductFilterValueMapFinal
        )

        productFilterCategoryItemListFinalChangeEvent.value = true
    }

    fun resetProductFilterCategoryItemListTempAndFinal() {
        productFilterMapper.resetProductFilterCategoryItemList(productFilterCategoryItemListFinal)
        resetProductFilterCategoryItemListTemp()
    }

    fun resetProductFilterCategoryItemListTemp() {
        productFilterCategoryItemListTemp.clear()
    }
}