package com.dudarenkavitali.jetsearchhelper.ui.search

import androidx.lifecycle.MutableLiveData
import com.dudarenkavitali.domain.entity.Feed
import com.dudarenkavitali.domain.uscases.SearchUseCase
import com.dudarenkavitali.jetsearchhelper.app.App
import com.dudarenkavitali.jetsearchhelper.base.BaseViewModel
import com.dudarenkavitali.jetsearchhelper.ui.main.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SearchViewModel : BaseViewModel<MainRouter>() {

    @Inject
    lateinit var searchUseCase: SearchUseCase

    init {
        App.appComponent.inject(this)
    }

    val feedsData = MutableLiveData<MutableList<Feed>>()

    fun searchFeeds(aircraft: String, destination: String, company: String) {
        val disposable = searchUseCase.getFeeds().subscribeBy(
            onSuccess = {
                val filteredList = mutableListOf<Feed>()
                if (aircraft.isNotEmpty())
                    filteredList.addAll(it.filter { feed -> feed.airCraft.contains(aircraft) })
                if (destination.isNotEmpty())
                    filteredList.addAll(it.filter { feed -> feed.to.contains(destination) })
                if (company.isNotEmpty())
                    filteredList.addAll(it.filter { feed -> feed.airCompany.contains(company) })
                feedsData.postValue(filteredList)
            },
            onError = {
                val i = 0
            }
        )
        addToDisposable(disposable)
    }

}