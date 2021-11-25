package com.dudarenkavitali.jetsearchhelper.ui.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.lifecycle.ViewModelProviders
import com.dudarenkavitali.jetsearchhelper.R
import com.dudarenkavitali.jetsearchhelper.base.BaseMvvmFragment
import com.dudarenkavitali.jetsearchhelper.ui.main.MainRouter
import com.dudarenkavitali.jetsearchhelper.ui.main.MainViewModel

class SearchFragment : BaseMvvmFragment<SearchViewModel, MainRouter>() {

    companion object {
        fun getInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun provideViewModel(): SearchViewModel {
        return ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSearch = view.findViewById<RelativeLayout>(R.id.btnSearch)
        val etDest = view.findViewById<EditText>(R.id.etDest)
        val etAircraft = view.findViewById<EditText>(R.id.etAircraft)
        val etCompany = view.findViewById<EditText>(R.id.etCompany)

        btnSearch.setOnClickListener {
            viewModel.searchFeeds(
                etAircraft.text.toString(),
                etDest.text.toString(),
                etCompany.text.toString()
            )
        }
    }

}