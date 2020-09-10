package com.rahulografy.axmecomm.ui.main.home.productfilter.adapter

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.rahulografy.axmecomm.databinding.ItemProductFilterType1Binding
import com.rahulografy.axmecomm.ui.main.home.productfilter.listener.ProductFilterEventListener
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterItem
import com.rahulografy.axmecomm.util.ext.toast

class ProductFilterViewHolder(
    private val binding: ItemProductFilterType1Binding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        productFilterCategoryItem: ProductFilterCategoryItem,
        productFilterEventListener: ProductFilterEventListener?
    ) {
        with(receiver = binding)
        {
            this.productFilterCategoryItem = productFilterCategoryItem
            this.productFilterEventListener = productFilterEventListener
            executePendingBindings()
        }

        initProductFilterItemsChipGroup(
            binding = binding,
            listProductFilterItem = productFilterCategoryItem.listProductFilterItem
        )
    }

    private fun initProductFilterItemsChipGroup(
        binding: ItemProductFilterType1Binding,
        listProductFilterItem: List<ProductFilterItem>?
    ) {
        binding.chipGroupProductFilter.apply {
            listProductFilterItem?.forEachIndexed { _, productFilterItem ->
                val chipProductFilter = Chip(context)
                chipProductFilter.id = productFilterItem.id
                chipProductFilter.text = productFilterItem.value
                addView(chipProductFilter)
            }

            isSingleSelection = false

            setOnCheckedChangeListener { _, checkedId ->
                if (checkedId >= 0) {
                    context.toast(
                        listProductFilterItem?.get(checkedId)?.value
                    )
                }
            }
        }
    }
}