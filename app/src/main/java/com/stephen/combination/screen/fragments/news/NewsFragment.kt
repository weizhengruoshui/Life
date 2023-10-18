package com.stephen.combination.screen.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stephen.combination.common.viewmodel.DataViewModelFactory
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.stephen.combination.databinding.FragmentNewsBinding
import com.stephen.combination.common.AppListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class NewsFragment : AppListFragment<MutableList<RecyclerViewTextAndImageItem>, NewsViewModel>() {

    @Inject
    lateinit var dataViewModelFactory: DataViewModelFactory

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getViewModel(): NewsViewModel {
        return ViewModelProvider(
            this,
            dataViewModelFactory
        ).get(NewsViewModel::class.java)
    }

    override fun getRecyclerView(): RecyclerView {
        return binding.rvNewsContent
    }

    override fun getListSwipeRefreshView(): SwipeRefreshLayout {
        return binding.newsSwipeRefresh
    }

    override fun resetViewBinding() {
        _binding = null
    }
}