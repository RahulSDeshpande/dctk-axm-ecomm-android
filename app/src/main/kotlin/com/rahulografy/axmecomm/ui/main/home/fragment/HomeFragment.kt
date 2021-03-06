package com.rahulografy.axmecomm.ui.main.home.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentHomeBinding
import com.rahulografy.axmecomm.ui.base.view.BaseFragment
import com.rahulografy.axmecomm.ui.main.home.fragment.adapter.ProductFragmentAdapter
import com.rahulografy.axmecomm.ui.main.home.productfilter.ProductFilterFragment
import com.rahulografy.axmecomm.util.ext.toast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    private var productFilterFragment: ProductFilterFragment? = null

    override val layoutRes get() = R.layout.fragment_home

    override val toolbarId: Int get() = R.id.toolbarHome

    override var viewModelClass = HomeFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (viewModel.isDataProcessing.get()) {
            toast(getString(R.string.msg_fetching_data_please_wait))
        } else {
            when (item.itemId) {
                R.id.menu_action_search, R.id.menu_action_filter -> openProductFilterFragment()
                R.id.menu_action_cart -> toast("WIP")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {
        if (isNetworkAvailable()) {
            viewModel.getProducts()
        }
    }

    override fun initSharedViewModelObservers() {

        viewModel.apply {
            productManager
                .mapCategoryWiseProductItemListFinal
                .observe(
                    lifecycleOwner = this@HomeFragment,
                    observer = Observer {
                        if (it != null) {
                            initViewPager(categories = it?.keys)
                            viewModel.isDataProcessing.set(false)
                        }
                    }
                )

            productFilterManager
                .productFilterCategoryItemListFinalChangeEvent
                .observe(
                    lifecycleOwner = this@HomeFragment,
                    observer = Observer {
                        if (it != null && it) {
                            productManager.updateProductItemListFinalBasedOnFilter()
                        }
                    }
                )
        }
    }

    private fun initViewPager(categories: Collection<String>?) {

        if (!categories.isNullOrEmpty()) {

            val productFragmentAdapter =
                ProductFragmentAdapter(
                    categories = categories.toList(),
                    childFragmentManager = childFragmentManager,
                    lifecycle = lifecycle
                )

            viewPager2Home?.apply {
                adapter = productFragmentAdapter
                offscreenPageLimit = categories.size
            }

            TabLayoutMediator(
                tabLayoutHome,
                viewPager2Home
            ) { tab, position ->
                tab.text = categories.toList()[position]
            }.attach()
        } else {
            toast("API error occurred while fetching products.")
        }
    }

    private fun openProductFilterFragment() {
        if (productFilterFragment == null) {
            productFilterFragment = ProductFilterFragment()
        }

        if (productFilterFragment?.isVisible != true) {
            productFilterFragment?.show(childFragmentManager, productFilterFragment?.tag)
        }
    }
}