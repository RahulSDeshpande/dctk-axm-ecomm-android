package com.rahulografy.axmecomm.ui.base.view

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun start() {}

    open fun stop() {}

    fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            if (it.isDisposed.not()) {
                compositeDisposable.add(disposable)
            }
        }
    }
}