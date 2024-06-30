package com.yaya.life.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaya.data.viewholderitems.RecyclerViewTextAndImageItem
import com.yaya.life.R
import com.yaya.life.basic.fragment.AppFragmentWithViewModel
import com.yaya.life.basic.viewmodel.DataViewModelFactory
import com.yaya.life.databinding.FragmentAppListBinding
import com.yaya.life.screen.list.adapter.RecyclerViewAdapter
import com.yaya.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppListFragment : AppFragmentWithViewModel<AppListViewModel>() {

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    @Inject
    lateinit var dataViewModelFactory: DataViewModelFactory

    private var _binding: FragmentAppListBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.loadData()
        fragmentViewModel.listData.observe(this) { listData ->
            updateList(listData)
        }
    }

    override fun provideFragmentViewModel(): Lazy<AppListViewModel> = viewModels<AppListViewModel> {
        dataViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun resetViewBinding() {
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()

        binding.newsSwipeRefresh.apply {
            setColorSchemeColors(
                resources.getColor(R.color.colorAccent, null),
                resources.getColor(R.color.colorSecondary, null),
                resources.getColor(R.color.colorPrimaryVariant, null),
                resources.getColor(R.color.colorSecondaryVariant, null)
            )
        }
    }

    private fun initializeRecyclerView() {
        binding.rvNewsContent.apply {
            setHasFixedSize(true)
            adapter = recyclerViewAdapter
            addOnScrollListener(LoadMoreScrollListener())
        }
    }


    private fun updateList(listData: List<RecyclerViewTextAndImageItem>) {
        binding.apply {
            newsSwipeRefresh.isRefreshing = false
            (rvNewsContent.adapter as? RecyclerViewAdapter)?.inflateData(listData)
        }
    }

    inner class LoadMoreScrollListener : RecyclerView.OnScrollListener() {

        /*
        newState:
        SCROLL_STATE_IDLE = 0
        SCROLL_STATE_DRAGGING = 1
        SCROLL_STATE_SETTLING = 2
         */
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
                LogUtils.logD(
                    javaClass.simpleName,
                    "lastVisibleItemPosition: $lastVisibleItemPosition, itemCount: $itemCount"
                )

                fragmentViewModel.loadMore(lastVisibleItemPosition, itemCount)
            }
        }
    }
}
