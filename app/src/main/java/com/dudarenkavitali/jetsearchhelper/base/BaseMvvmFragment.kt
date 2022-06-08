package com.dudarenkavitali.jetsearchhelper.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseMvvmFragment <VM : BaseViewModel<R>, R : BaseRouter<*>> : BaseFragment() {

    protected lateinit var viewModel: VM
    abstract fun provideViewModel(): VM
    protected var router: R? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = provideViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is BaseMvvmActivity<*, *>) {
            router = (activity as BaseMvvmActivity<*, *>).router as R
        }
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