package com.rahulografy.axmecomm.ui.main.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetResponse
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.ui.main.home.fragment.HomeViewModel
import com.rahulografy.axmecomm.ui.main.home.fragment.listener.ProductEventListener

class MobileHandsetsResponse(
    private var mobileHandsetsResponse: MobileHandsetsResponse,
    private val homeViewModel: HomeViewModel?
) : ListAdapter<MobileHandsetResponse, ProductViewHolder>(ProductsDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_type_1, parent, false
            )
        )

    override fun getItemCount() = mobileHandsetsResponse.size

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) =
        viewHolder.bind(
            mobileHandsetResponse = mobileHandsetsResponse[position],
            productEventListener = object : ProductEventListener {

                override fun onItemClicked(mobileHandsetResponse: MobileHandsetResponse) {
                    homeViewModel?.openMobileHandsetDetails(mobileHandsetResponse)
                }
            }
        )

    fun setData(mobileHandsetsResponse: MobileHandsetsResponse) {
        this.mobileHandsetsResponse = mobileHandsetsResponse
        submitList(mobileHandsetsResponse)
    }
}