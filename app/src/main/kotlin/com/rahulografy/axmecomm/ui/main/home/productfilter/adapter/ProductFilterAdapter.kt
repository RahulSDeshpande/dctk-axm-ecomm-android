package com.rahulografy.axmecomm.ui.main.home.productfilter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.ui.base.adapter.BaseListAdapter
import com.rahulografy.axmecomm.ui.main.home.productfilter.ProductFilterFragmentViewModel
import com.rahulografy.axmecomm.ui.main.home.productfilter.listener.ProductFilterEventListener
import com.rahulografy.axmecomm.ui.main.home.productfilter.model.ProductFilterCategoryItem

// TODO | WIP | CREATE A BaseAdapter WHICH SHOULD HAVE ALL COMMON ADAPTER FEATURES
class ProductFilterAdapter(
    private var listProductFilterCategoryItem: ArrayList<ProductFilterCategoryItem> = arrayListOf(),
    private val productFragmentViewModel: ProductFilterFragmentViewModel?
) : BaseListAdapter<ProductFilterCategoryItem, ProductFilterViewHolder>(
    ProductFilterCategoryDiffUtilItemCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductFilterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_filter_type_1,
                parent,
                false
            )
        )

    override fun getItemCount() = listProductFilterCategoryItem.size

    override fun onBindViewHolder(viewHolder: ProductFilterViewHolder, position: Int) =
        viewHolder.bind(
            productFilterCategoryItem = listProductFilterCategoryItem[position],
            productFilterEventListener = object : ProductFilterEventListener {
                override fun onFilterCancelled() {}
                override fun onFilterSaved() {}
            }
        )

    override fun setData(data: List<ProductFilterCategoryItem>?) {
        listProductFilterCategoryItem.clear()
        data?.let { listProductFilterCategoryItem.addAll(it) }
        submitList(listProductFilterCategoryItem)
    }

    override fun addData(data: List<ProductFilterCategoryItem>?) {
        data?.let {
            listProductFilterCategoryItem.addAll(it)
            submitList(listProductFilterCategoryItem)
        }
    }
}