package com.rahulografy.axmecomm.ui.main.home.productfilter.mapper

import com.rahulografy.axmecomm.ui.base.mapper.BaseMapper
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterType
import com.rahulografy.axmecomm.util.ext.addIfNotExists
import com.rahulografy.axmecomm.util.ext.toList
import javax.inject.Inject

class ProductFilterMapper
@Inject constructor() : BaseMapper<List<ProductItem>, List<ProductFilterCategoryItem>> {

    override fun map(input: List<ProductItem>?): ArrayList<ProductFilterCategoryItem>? =
        input?.mapProductFilterCategoryItem()

    // TODO | FIND MORE OPTIMUM SOLUTION
    private fun List<ProductItem>.mapProductFilterCategoryItem(): ArrayList<ProductFilterCategoryItem>? {
        // NOT REQUIRED AS WE ALREADY HAVE 2 TABS SEPARATE FOR BRANDS
        // val brandList = map { it.brand.toString() }.distinct()
        val phoneList = map { it.phone.toString() }.distinct()

        // TODO | PRICE SHOULD BE MAPPED IN INTEGER FORMAT FOR BETTER FILTERING
        val priceList = map { it.priceEurInt.toString() }.distinct()

        val simList = map { it.sim.toString() }.distinct()
        val gpsList = map { it.gps.toString() }.distinct()
        val audioJackList = map { it.audioJack.toString() }.distinct()

        return arrayListOf<ProductFilterCategoryItem>().apply {

            var id = -1

            /* NOT REQUIRED AS WE ALREADY HAVE 2 TABS SEPARATE FOR BRANDS
            add(
                ProductFilterCategoryItem(
                    value = "brand",
                    title = "Brand",
                    productFilterItemList = brandList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )
            */

            add(
                ProductFilterCategoryItem(
                    id = ++id,
                    value = "phone",
                    title = "Phone Name",
                    productFilterItemList = phoneList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    id = ++id,
                    value = "price",
                    title = "Price",
                    productFilterItemList = priceList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    id = ++id,
                    value = "sim",
                    title = "SIM",
                    productFilterItemList = simList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    id = ++id,
                    value = "gps",
                    title = "GPS",
                    productFilterItemList = gpsList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    id = ++id,
                    value = "audioJack",
                    title = "Audio Jack",
                    productFilterItemList = audioJackList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_SINGLE
                )
            )
        }
    }

    private fun List<String>.mapProductFilterItem(): List<ProductFilterItem> {

        val productFilterItemList = arrayListOf<ProductFilterItem>()

        forEachIndexed { index, value ->
            productFilterItemList.add(
                ProductFilterItem(id = index, value = value)
            )
        }

        return productFilterItemList
    }

    fun saveProductFilterCategoryItemListTempToFinal(
        productFilterCategoryItemListTemp: ArrayList<ProductFilterCategoryItem>,
        productFilterCategoryItemListFinal: List<ProductFilterCategoryItem>?,
        productFilterCategoryWiseProductFilterValueMapFinal: HashMap<String, ArrayList<String>>
    ) {
        productFilterCategoryWiseProductFilterValueMapFinal.clear()

        val productFilterCategoryValueListTemp =
            productFilterCategoryItemListTemp
                .groupBy { it.value }
                .keys
                .toList()

        productFilterCategoryItemListFinal
            ?.filter { productFilterCategoryValueListTemp.contains(it.value) }
            ?.forEach { productFilterCategoryItemFinal ->

                val productFilterCategoryItemTemp = productFilterCategoryItemListTemp
                    .find { it.value == productFilterCategoryItemFinal.value }

                if (productFilterCategoryItemTemp != null) {

                    val productFilterItemListTemp =
                        productFilterCategoryItemTemp.productFilterItemList

                    val productFilterValueListTemp =
                        productFilterItemListTemp
                            .groupBy { it.value }
                            .keys
                            .toList()

                    productFilterCategoryItemFinal
                        .productFilterItemList
                        .filter { productFilterValueListTemp.contains(it.value) }
                        .forEachIndexed { index, productFilterItem ->
                            val isSelectedTemp = productFilterItemListTemp[index].isSelected
                            productFilterItem.isSelected = isSelectedTemp

                            if (isSelectedTemp) {
                                updateProductFilterCategoryWiseProductFilterValueMap(
                                    categoryValue = productFilterCategoryItemTemp.value,
                                    filterValue = productFilterItem.value,
                                    productFilterCategoryWiseProductFilterValueMapFinal =
                                    productFilterCategoryWiseProductFilterValueMapFinal
                                )
                            }
                        }
                }
            }
    }

    private fun updateProductFilterCategoryWiseProductFilterValueMap(
        categoryValue: String,
        filterValue: String,
        productFilterCategoryWiseProductFilterValueMapFinal: HashMap<String, ArrayList<String>>
    ) {
        var list = productFilterCategoryWiseProductFilterValueMapFinal[categoryValue]
        if (list == null) {
            list = arrayListOf()
        }
        list.addIfNotExists(filterValue)
        productFilterCategoryWiseProductFilterValueMapFinal[categoryValue] = list
    }

    fun resetProductFilterCategoryItemList(productFilterCategoryItemList: List<ProductFilterCategoryItem>) {
        productFilterCategoryItemList.forEach { productFilterCategoryItem ->
            productFilterCategoryItem.productFilterItemList.forEach { productFilterItem ->
                productFilterItem.isSelected = false
            }
        }
    }
}