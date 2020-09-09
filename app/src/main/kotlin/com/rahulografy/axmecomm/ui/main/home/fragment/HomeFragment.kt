package com.rahulografy.axmecomm.ui.main.home.fragment

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

    override var viewModelClass = HomeFragmentViewModel::class.java

    override val bindingVariable = BR.viewModel

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