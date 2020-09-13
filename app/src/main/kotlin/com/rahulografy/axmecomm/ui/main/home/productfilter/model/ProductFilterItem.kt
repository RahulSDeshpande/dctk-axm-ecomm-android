package com.rahulografy.axmecomm.ui.main.home.productfilter.model

data class ProductFilterItem(
    val id: Int,
    val value: String,
    var isSelected: Boolean = false
)