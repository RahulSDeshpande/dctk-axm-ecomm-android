package com.rahulografy.axmecomm.ui.main.home

import android.os.Bundle
import com.rahulografy.axmecomm.R
import com.rahulografy.axmecomm.databinding.ActivityMainBinding
import com.rahulografy.axmecomm.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutRes: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}