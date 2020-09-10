package com.rahulografy.axmecomm.ui.main.home.fragment.adapter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rahulografy.axmecomm.ui.main.home.product.ProductFragment.Companion.initProductFragment

class ProductFragmentAdapter(
    private val categories: List<String>,
    childFragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(childFragmentManager, lifecycle) {

    override fun createFragment(position: Int) = initProductFragment(categories[position])

    override fun getItemCount() = categories.size
}