package com.rahulografy.axmecomm.ui.main.home.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.ui.base.adapter.BaseListAdapter
import com.rahulografy.axmecomm.ui.base.model.BaseProductItem
import com.rahulografy.axmecomm.ui.main.home.product.ProductFragmentViewModel
import com.rahulografy.axmecomm.ui.main.home.product.listener.ProductEventListener
import com.rahulografy.axmecomm.ui.main.home.product.model.ProductItem
import com.rahulografy.axmecomm.ui.main.home.product.model.toProductItem

// TODO | WIP | CREATE A BaseAdapter WHICH SHOULD HAVE ALL COMMON ADAPTER FEATURES
class ProductAdapter(
    private var listProductItem: ArrayList<ProductItem> = arrayListOf(),
    private val productFragmentViewModel: ProductFragmentViewModel?
) : BaseListAdapter<ProductItem, ProductViewHolder>(ProductsDiffUtilItemCallback()) {

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
                    productFragmentViewModel?.openProductDetails(productItem = item.toProductItem())
                }
            }
        )

    override fun setData(data: List<ProductItem>?) {
        listProductItem.clear()
        data?.let { listProductItem.addAll(it) }
        submitList(listProductItem)
    }

    override fun addData(data: List<ProductItem>?) {
        data?.let {
            listProductItem.addAll(it)
            submitList(listProductItem)
        }
    }
}