package com.rahulografy.axmecomm.ui.main.home.productfilter.mapper

import com.rahulografy.axmecomm.ui.base.mapper.BaseMapper
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterType
import javax.inject.Inject

class ProductFilterMapper
@Inject constructor() : BaseMapper<List<ProductItem>, List<ProductFilterCategoryItem>> {

    override fun map(input: List<ProductItem>?): List<ProductFilterCategoryItem>? =
        input?.mapProductFilterCategoryItem()

    // TODO | FIND MORE OPTIMUM SOLUTION
    private fun List<ProductItem>.mapProductFilterCategoryItem(): List<ProductFilterCategoryItem>? {
        val brandList = map { it.brand.toString() }.distinct()
        val phoneList = map { it.phone.toString() }.distinct()

        // TODO | PRICE SHOULD BE MAPPED IN INTEGER FORMAT FOR BETTER FILTERING
        val priceList = map { it.priceEurInt.toString() }.distinct()

        val simList = map { it.sim.toString() }.distinct()
        val gpsList = map { it.gps.toString() }.distinct()
        val audioJackList = map { it.audioJack.toString() }.distinct()

        return arrayListOf<ProductFilterCategoryItem>().apply {

            add(
                ProductFilterCategoryItem(
                    value = "brand",
                    title = "Brand",
                    listProductFilterItem = brandList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    value = "phone",
                    title = "Phone Name",
                    listProductFilterItem = phoneList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    value = "price",
                    title = "Price",
                    listProductFilterItem = priceList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    value = "sim",
                    title = "SIM",
                    listProductFilterItem = simList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    value = "gps",
                    title = "GPS",
                    listProductFilterItem = gpsList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_MULTIPLE
                )
            )

            add(
                ProductFilterCategoryItem(
                    value = "audioJack",
                    title = "Audio Jack",
                    listProductFilterItem = audioJackList.mapProductFilterItem(),
                    productFilterType = ProductFilterType.SELECTION_SINGLE
                )
            )
        }
    }

    private fun List<String>.mapProductFilterItem(): List<ProductFilterItem> {

        val listProductFilterItem = arrayListOf<ProductFilterItem>()

        forEachIndexed { index, value ->
            listProductFilterItem.add(
                ProductFilterItem(id = index, value = value)
            )
        }

        return listProductFilterItem
    }
}