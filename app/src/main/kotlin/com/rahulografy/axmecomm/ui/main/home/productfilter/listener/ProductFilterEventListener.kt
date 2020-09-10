package com.rahulografy.axmecomm.ui.main.home.productfilter.listener

import com.rahulografy.axmecomm.ui.base.model.BaseProductItem

interface ProductFilterEventListener {

    fun onFilterCancelled()

    fun onFilterSaved()

    fun onFilterItemSelected(item: BaseProductItem) {}

    fun onFilterItemDeselected(item: BaseProductItem) {}
}