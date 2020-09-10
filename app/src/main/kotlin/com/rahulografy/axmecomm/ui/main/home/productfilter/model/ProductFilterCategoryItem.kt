package com.rahulografy.axmecomm.ui.main.home.productfilter.model

data class ProductFilterCategoryItem(
    val value: String? = null,
    val title: String? = null,
    val listProductFilterItem: List<ProductFilterItem> = listOf(),
    val isMandatory: Boolean = false,
    val productFilterType: ProductFilterType
)