package com.rahulografy.axmecomm.ui.base.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseListAdapter<D, VH : ViewHolder>(diffCallback: DiffUtil.ItemCallback<D>) :
    ListAdapter<D, VH>(diffCallback) {

    abstract fun setData(data: List<D>?)

    abstract fun addData(data: List<D>?)
}