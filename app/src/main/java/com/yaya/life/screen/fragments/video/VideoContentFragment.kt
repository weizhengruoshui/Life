package com.yaya.life.screen.fragments.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yaya.life.common.AppListFragment
import com.yaya.life.common.viewmodel.DataViewModelFactory
import com.yaya.life.databinding.FragmentVideoBinding
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class VideoContentFragment : AppListFragment<MutableList<RecyclerViewTextAndImageItem>, VideoContentViewModel>() {

    @Inject
    lateinit var viewModelFactory: DataViewModelFactory

    private var _binding: FragmentVideoBinding? = null

    private val binding get() = _binding!!

    override fun getRecyclerView(): RecyclerView {
        return binding.videoList
    }

    override fun getListSwipeRefreshView(): SwipeRefreshLayout? {
        return null
    }

    override fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getViewModel(): VideoContentViewModel {
        return ViewModelProvider(this, viewModelFactory).get(VideoContentViewModel::class.java)
    }

    override fun resetViewBinding() {
        _binding = null
    }
}