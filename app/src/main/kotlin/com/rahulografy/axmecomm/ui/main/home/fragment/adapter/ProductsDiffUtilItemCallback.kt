package com.rahulografy.axmecomm.ui.main.home.fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetResponse

class ProductsDiffUtilItemCallback : DiffUtil.ItemCallback<MobileHandsetResponse>() {

    override fun areItemsTheSame(
        oldItem: MobileHandsetResponse,
        newItem: MobileHandsetResponse
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MobileHandsetResponse,
        newItem: MobileHandsetResponse
    ) = oldItem.id == newItem.id
}