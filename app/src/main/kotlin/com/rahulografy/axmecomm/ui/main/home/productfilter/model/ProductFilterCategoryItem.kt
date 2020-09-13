package com.rahulografy.axmecomm.ui.main.home.productfilter.model

data class ProductFilterCategoryItem(
    val id: Int,
    val value: String,
    val title: String,
    val productFilterItemList: List<ProductFilterItem> = listOf(),
    val productFilterItemValueList: List<String> = listOf(),
    val isMandatory: Boolean = false,
    val productFilterType: ProductFilterType
)