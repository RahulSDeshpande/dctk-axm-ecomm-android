package com.rahulografy.axmecomm.ui.main.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.ui.base.model.BaseProductItem
import com.rahulografy.axmecomm.ui.main.home.fragment.HomeViewModel
import com.rahulografy.axmecomm.ui.main.home.fragment.listener.ProductEventListener
import com.rahulografy.axmecomm.ui.main.home.fragment.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.fragment.model.toProductItem

// TODO | WIP | CREATE A BaseAdapter WHICH SHOULD HAVE ALL COMMON ADAPTER FEATURES
class ProductsAdapter(
    private var listProductItem: ArrayList<ProductItem>,
    private val homeViewModel: HomeViewModel?
) : ListAdapter<ProductItem, ProductViewHolder>(ProductsDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_type_1, parent, false
            )
        )

    override fun getItemCount() = listProductItem.size

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) =
        viewHolder.bind(
            productItem = listProductItem[position],
            productEventListener = object : ProductEventListener {

                override fun onItemClicked(item: BaseProductItem) {
                    homeViewModel?.openProductDetails(productItem = item.toProductItem())
                }
            }
        )

    fun setData(listProductItemNew: ArrayList<ProductItem>) {
        listProductItem = listProductItemNew
        submitList(listProductItem)
    }

    fun addData(listProductItemDelta: List<ProductItem>) {
        listProductItem.addAll(listProductItemDelta)
        submitList(listProductItem)
    }
}