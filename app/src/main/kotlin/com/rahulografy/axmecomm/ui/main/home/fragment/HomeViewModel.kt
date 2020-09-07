package com.rahulografy.axmecomm.ui.main.home.fragment

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetResponse
import com.rahulografy.axmecomm.data.remote.mobilehandsets.model.MobileHandsetsResponse
import com.rahulografy.axmecomm.data.repository.mobilehandsets.MobileHandsetsRepository
import com.rahulografy.axmecomm.ui.base.BaseViewModel
import com.rahulografy.axmecomm.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val mobileHandsetsRepository: MobileHandsetsRepository
) : BaseViewModel() {

    val isApiCallInProgress = ObservableBoolean(false)

    val mobileHandsets = ObservableField<MobileHandsetsResponse>()

    val onMobileHandsetClickEvent = SingleLiveEvent<MobileHandsetResponse>()

    private var disposable: Disposable? = null

    override fun start() {
        getMobileHandsets()
    }

    override fun stop() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun refresh() {
        getMobileHandsets()
    }

    private fun getMobileHandsets() {
        disposable =
            mobileHandsetsRepository
                .getMobileHandsets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isApiCallInProgress.set(true) }
                .doAfterTerminate { isApiCallInProgress.set(false) }
                .subscribe({
                    mobileHandsets.set(it)
                }, { error ->
                    error.printStackTrace()
                })
    }
}