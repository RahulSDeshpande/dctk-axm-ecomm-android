package com.rahulografy.axmecomm.ui.main.home.productfilter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem

class ProductFilterCategoryDiffUtilItemCallback :
    DiffUtil.ItemCallback<ProductFilterCategoryItem>() {

    override fun areItemsTheSame(
        oldItem: ProductFilterCategoryItem,
        newItem: ProductFilterCategoryItem
    ) = oldItem.value == newItem.value

    // TODO | COMPARE CONTENTS THOROUGHLY
    override fun areContentsTheSame(
        oldItem: ProductFilterCategoryItem,
        newItem: ProductFilterCategoryItem
    ) = oldItem.value == newItem.value
}