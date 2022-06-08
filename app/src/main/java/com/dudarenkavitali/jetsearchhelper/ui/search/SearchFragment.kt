package com.dudarenkavitali.jetsearchhelper.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dudarenkavitali.jetsearchhelper.base.BaseMvvmFragment
import com.dudarenkavitali.jetsearchhelper.databinding.FragmentSearchBinding
import com.dudarenkavitali.jetsearchhelper.ui.main.MainRouter
import com.dudarenkavitali.jetsearchhelper.utils.DefaultUtils
import com.yandex.mapkit.MapKitFactory

private const val MAP_DIALOG = "MapDialog"

class SearchFragment : BaseMvvmFragment<SearchViewModel, MainRouter>() {

    private val binding: FragmentSearchBinding by viewBinding(CreateMethod.INFLATE)
    private val adapter = SearchResultsAdapter()

    companion object {
        fun getInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun provideViewModel(): SearchViewModel {
        return ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MapKitFactory.setApiKey("b6a54cbc-8999-49dc-baa0-f1bf09ff269b")
        binding.rvResults.layoutManager = LinearLayoutManager(requireContext())
        adapter.initListener {
            val dialog = MapDialog.newInstance(it.latitude, it.longitude)
            val ft: FragmentTransaction = childFragmentManager.beginTransaction()
            val prev: Fragment? = childFragmentManager.findFragmentByTag(MAP_DIALOG)
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            dialog.show(ft, MAP_DIALOG)
        }
        binding.rvResults.adapter = adapter

        binding.btnSearch.setOnClickListener {
            DefaultUtils().hideKeyboard(requireContext(), requireView())
            binding.progressBar.visibility = View.VISIBLE
            viewModel.searchFeeds(
                binding.etAircraft.text.toString(),
                binding.etDest.text.toString(),
                binding.etCompany.text.toString()
            )
        }

        viewModel.feedsData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.rvResults.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
            binding.tvNoData.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            adapter.setItems(it)
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {

        super.onStop()
    }

}