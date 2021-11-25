package com.dudarenkavitali.jetsearchhelper.ui.main

import com.dudarenkavitali.jetsearchhelper.R
import com.dudarenkavitali.jetsearchhelper.base.BaseRouter
import com.dudarenkavitali.jetsearchhelper.ui.search.SearchFragment

class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {

    fun goToSearch() {
        replaceFragment(activity.supportFragmentManager, SearchFragment.getInstance(), R.id.flMain, false)
    }

}