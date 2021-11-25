package com.dudarenkavitali.jetsearchhelper.di

import com.dudarenkavitali.jetsearchhelper.ui.search.SearchViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(searchViewModel: SearchViewModel)

}