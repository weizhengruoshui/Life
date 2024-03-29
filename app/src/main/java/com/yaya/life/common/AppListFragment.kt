package com.yaya.life.common

import android.os.Parcelable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yaya.life.R
import com.yaya.life.common.list.adapter.RecyclerViewAdapter
import com.yaya.life.common.viewmodel.ListViewModel
import com.yaya.utils.LogUtils
import javax.inject.Inject

abstract class AppListFragment<itemDataType : MutableList<out Parcelable>, listViewModel : ListViewModel<itemDataType>> :
    AppFragment<itemDataType, listViewModel>() {

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun bindObserver() {
        super.bindObserver()
        val observer: Observer<itemDataType> = Observer { data ->
            loadMoreData(data)
        }
        fragmentViewModel.moreData.observe(this, observer)
    }

    override fun attributeViews() {
        initializeRecyclerView()

        getListSwipeRefreshView()?.apply {
            setColorSchemeColors(
                resources.getColor(R.color.colorAccent, null),
                resources.getColor(R.color.colorSecondary, null),
                resources.getColor(R.color.colorPrimaryVariant, null),
                resources.getColor(R.color.colorSecondaryVariant, null)
            )
            setOnRefreshListener {
                fragmentViewModel.loadData()
            }
        }
    }

    open fun initializeRecyclerView() {
        getRecyclerView().apply {
            setHasFixedSize(true)
            adapter = recyclerViewAdapter
            addOnScrollListener(LoadMoreScrollListener())
        }
    }

    fun scrollToTop() {
        getRecyclerView().smoothScrollToPosition(0)
    }

    override fun populateData(data: itemDataType) {
        getListSwipeRefreshView()?.isRefreshing = false
        getRecyclerView().apply {
            (adapter as RecyclerViewAdapter).inflateData(data.toMutableList())
        }
    }

    private fun loadMoreData(data: itemDataType) {
        getRecyclerView().apply {
            (adapter as RecyclerViewAdapter).loadMoreItems(data.toMutableList())
        }
    }

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getListSwipeRefreshView(): SwipeRefreshLayout?

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
