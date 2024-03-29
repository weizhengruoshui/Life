package com.yaya.life.screen.fragments.video.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yaya.life.common.AppListFragment
import com.yaya.life.common.viewmodel.DataViewModelFactory
import com.yaya.life.databinding.FragmentVideoListBinding
import com.yaya.data.viewholder.RecyclerViewVideoItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class VideoListFragment :
    AppListFragment<MutableList<RecyclerViewVideoItem>, VideoListViewModel>() {

    @Inject
    lateinit var dataViewModelFactory: DataViewModelFactory

    private var _binding: FragmentVideoListBinding? = null

    private val binding get() = _binding!!

    override fun bindObserver() {
        super.bindObserver()
        lifecycle.addObserver(fragmentViewModel)
    }

    override fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun attributeViews() {
        super.attributeViews()
        binding.rvVideosContent.addOnScrollListener(VideoListScrollListener())
    }

    override fun onResume() {
        super.onResume()
        if (fragmentViewModel.shouldRunResume) {
            fragmentViewModel.firstPlayViewHolder(binding.rvVideosContent)
        }
    }

    override fun onPause() {
        super.onPause()
        fragmentViewModel.stopPlayingViewHolder(binding.rvVideosContent)
    }

    override fun populateData(data: MutableList<RecyclerViewVideoItem>) {
        super.populateData(data)
        fragmentViewModel.firstPlayViewHolder(binding.rvVideosContent)
    }

    override fun getViewModel(): VideoListViewModel {
        return ViewModelProvider(
            this,
            dataViewModelFactory
        ).get(VideoListViewModel::class.java)
    }

    override fun getRecyclerView(): RecyclerView {
        return binding.rvVideosContent
    }

    override fun getListSwipeRefreshView(): SwipeRefreshLayout {
        return binding.videosSwipeRefresh
    }

    override fun resetViewBinding() {
        _binding = null
    }

    inner class VideoListScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE ->
                    fragmentViewModel.playTheFirstCompletelyShowViewHolder(recyclerView)
                RecyclerView.SCROLL_STATE_DRAGGING ->
                    fragmentViewModel.unbindFirstPlayVideoSchedule()
            }
        }
    }
}