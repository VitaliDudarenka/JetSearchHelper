package com.dudarenkavitali.jetsearchhelper.base

import androidx.fragment.app.FragmentActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : FragmentActivity() {

    protected open val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}