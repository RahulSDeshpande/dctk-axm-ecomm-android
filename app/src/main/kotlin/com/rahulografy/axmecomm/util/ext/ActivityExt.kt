package com.rahulografy.axmecomm.util.ext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

inline fun AppCompatActivity.addFragment(
    containerViewId: Int,
    function: () -> Fragment
): Fragment? = function().apply {
    supportFragmentManager?.beginTransaction()?.add(containerViewId, this)?.commit()
}

inline fun AppCompatActivity.addFragment(
    containerViewId: Int,
    bundle: Bundle,
    function: () -> Fragment
): Fragment? = function().apply {
    arguments = bundle
    supportFragmentManager?.beginTransaction()?.add(containerViewId, this)?.commit()
}

inline fun AppCompatActivity.replaceFragment(
    containerViewId: Int,
    function: () -> Fragment
): Fragment? = function().apply {
    supportFragmentManager?.beginTransaction()?.replace(containerViewId, this)?.commit()
}