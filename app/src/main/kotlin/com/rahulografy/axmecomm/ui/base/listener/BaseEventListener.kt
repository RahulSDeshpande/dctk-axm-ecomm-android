package com.rahulografy.axmecomm.ui.base.listener

import com.rahulografy.axmecomm.ui.base.model.BaseProductItem

interface BaseEventListener {

    fun onItemClicked(item: BaseProductItem)

    fun onItemLongClicked(item: BaseProductItem) {}

    fun onItemOptionsClicked(item: BaseProductItem) {}

    fun onItemLeftSwiped(item: BaseProductItem) {}

    fun onItemRightSwiped(item: BaseProductItem) {}
}