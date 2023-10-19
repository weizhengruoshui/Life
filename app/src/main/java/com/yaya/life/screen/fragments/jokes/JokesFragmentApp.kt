package com.yaya.life.screen.fragments.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yaya.life.common.viewmodel.DataViewModelFactory
import com.yaya.data.viewholder.RecyclerViewTextItem
import com.yaya.life.databinding.FragmentJokesBinding
import com.yaya.life.common.AppListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class JokesFragmentApp : AppListFragment<MutableList<RecyclerViewTextItem>, JokesViewModel>() {

    @Inject
    lateinit var dataViewModelFactory: DataViewModelFactory

    private var _binding: FragmentJokesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getViewModel(): JokesViewModel {
        return ViewModelProvider(
            this,
            dataViewModelFactory
        ).get(JokesViewModel::class.java)
    }

    override fun getRecyclerView(): RecyclerView {
        return binding.rvJokesContent
    }

    override fun getListSwipeRefreshView(): SwipeRefreshLayout {
        return binding.jokesSwipeRefresh
    }

    override fun resetViewBinding() {
        _binding = null
    }
}