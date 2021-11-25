package com.dudarenkavitali.jetsearchhelper.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.dudarenkavitali.jetsearchhelper.R
import com.dudarenkavitali.jetsearchhelper.base.BaseMvvmActivity

class MainActivity : BaseMvvmActivity<MainViewModel, MainRouter>() {

    override fun provideViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun provideRouter(): MainRouter {
        return MainRouter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.goToSearch()
    }

}