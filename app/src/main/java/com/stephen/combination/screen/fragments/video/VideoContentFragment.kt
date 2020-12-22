package com.stephen.combination.screen.fragments.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stephen.combination.common.AppListFragment
import com.stephen.combination.common.viewmodel.DataViewModelFactory
import com.stephen.combination.databinding.FragmentVideoBinding
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import javax.inject.Inject

class VideoContentFragment : AppListFragment<MutableList<RecyclerViewTextAndImageItem>, VideoContentViewModel>() {

    @Inject
    lateinit var viewModelFactory: DataViewModelFactory

    private var _binding: FragmentVideoBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance(): VideoContentFragment {
            return VideoContentFragment()
        }
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        listFragmentComponent.inject(this)
    }

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