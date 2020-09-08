package com.rahulografy.axmecomm.ui.main.home.activity

import com.rahulografy.axmecomm.BR
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.ActivityMainBinding
import com.rahulografy.axmecomm.ui.base.view.BaseActivity

class HomeActivity : BaseActivity<ActivityMainBinding, HomeActivityViewModel>() {

    override val layoutRes: Int get() = R.layout.activity_main

    override val viewModelClass get() = HomeActivityViewModel::class.java

    override val bindingVariable get() = BR.viewModel

    override fun initUi() {
    }
}