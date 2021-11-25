package com.dudarenkavitali.jetsearchhelper.base

import android.os.Bundle

abstract class BaseMvvmActivity<VM : BaseViewModel<R>, R : BaseRouter<*>> : BaseActivity() {

    protected lateinit var viewModel: VM
    public lateinit var router: R
    abstract fun provideViewModel(): VM
    abstract fun provideLayoutId(): Int
    abstract fun provideRouter(): R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(provideLayoutId())
        viewModel = provideViewModel()
        router = provideRouter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.addRouter(router)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeRouter()
    }
}