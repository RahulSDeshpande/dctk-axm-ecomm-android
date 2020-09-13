package com.rahulografy.axmecomm.ui.main.home.productfilter.adapter

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.rahulografy.axmecomm.databinding.ItemProductFilterType1Binding
import com.rahulografy.axmecomm.ui.main.home.productfilter.listener.ProductFilterEventListener
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterType

class ProductFilterViewHolder(
    private val binding: ItemProductFilterType1Binding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        productFilterCategoryItem: ProductFilterCategoryItem,
        productFilterEventListener: ProductFilterEventListener?
    ) {
        with(receiver = binding) {
            this.productFilterCategoryItem = productFilterCategoryItem
            this.productFilterEventListener = productFilterEventListener
            executePendingBindings()
        }

        initProductFilterItemsChipGroup(
            binding = binding,
            productFilterCategoryItem = productFilterCategoryItem,
            productFilterEventListener = productFilterEventListener
        )
    }

    private fun initProductFilterItemsChipGroup(
        binding: ItemProductFilterType1Binding,
        productFilterCategoryItem: ProductFilterCategoryItem,
        productFilterEventListener: ProductFilterEventListener?
    ) {
        binding.chipGroupProductFilter.apply {

            productFilterCategoryItem.apply {

                productFilterItemList.forEachIndexed { _, productFilterItem ->

                    Chip(context).apply {

                        id = productFilterItem.id
                        text = productFilterItem.value
                        isChecked = productFilterItem.isSelected

                        setOnCheckedChangeListener { _, isChecked ->

                            productFilterEventListener?.onProductFilterItemSelected(
                                productFilterCategoryItem = productFilterCategoryItem,
                                productCategoryItemId = productFilterCategoryItem.id,
                                productCategoryItemValue = productFilterCategoryItem.value,
                                productItemId = productFilterItem.id,
                                productItemValue = productFilterItem.value,
                                isSelected = isChecked
                            )
                        }

                        addView(this)
                    }
                }

                (productFilterType == ProductFilterType.SELECTION_SINGLE).let {
                    isSingleSelection = it
                    isSelectionRequired = it
                }

//                setOnCheckedChangeListener { _, checkedId ->
//                    if (checkedId >= 0) {
//                        productFilterCategoryItem.productFilterItemList[adapterPosition].isSelected=checkedId
//                        productFilterEventListener?.onProductFilterItemSelected(
//                            productFilterCategoryItem
//                        )
//                    }
//                }
            }
        }
    }
}