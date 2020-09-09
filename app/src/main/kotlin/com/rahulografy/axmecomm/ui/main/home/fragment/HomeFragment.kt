package com.rahulografy.axmecomm.ui.main.home.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.FragmentHomeBinding
import com.rahulografy.axmecomm.ui.base.view.BaseFragment
import com.rahulografy.axmecomm.ui.main.home.ProductFragmentAdapter
import com.rahulografy.axmecomm.util.ext.toast
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_home

    override val toolbarId: Int get() = R.id.toolbarHome

    override var viewModelClass = HomeFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (viewModel.isApiCallInProgress.get()) {
            toast("Data is being fetched, please wait...")
        } else {
            when (item.itemId) {
                R.id.menu_action_search -> {
                }
                R.id.menu_action_filter -> {
                }
                R.id.menu_action_cart -> {
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {
        viewModel.getProducts()
    }

    override fun initSharedViewModelObservers() {

        viewModel
            .productsManager
            .mapCategoryWiseProducts
            .observe(
                lifecycleOwner = this,
                observer = Observer {
                    initViewPager(categories = it?.keys)
                    viewModel.isApiCallInProgress.set(false)
                }
            )
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
                registerOnPageChangeCallback(
                    object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            toast("Selected page position: $position")
                        }
                    }
                )

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
}