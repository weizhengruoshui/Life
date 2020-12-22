package com.stephen.combination.common

import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stephen.combination.R
import com.stephen.combination.dagger.component.ListFragmentComponent
import com.stephen.combination.dagger.module.FragmentModule
import com.stephen.combination.dagger.module.RecyclerViewAdapterModule
import com.stephen.combination.common.list.adapter.RecyclerViewAdapter
import com.stephen.combination.common.viewmodel.ListViewModel
import com.stephen.combination.dagger.component.DaggerListFragmentComponent
import javax.inject.Inject

abstract class AppListFragment<I : MutableList<out Parcelable>, K : ListViewModel<I>> :
    BaseFragment<I, K>() {

    lateinit var listFragmentComponent: ListFragmentComponent

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private val refreshLayout: SwipeRefreshLayout? by lazy {
        getListSwipeRefreshView()
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        initListFragmentComponent()

    }

    override fun attributeViews() {
        initializeRecyclerView()

        refreshLayout?.apply {
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
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
    }

    fun scrollToTop() {
        getRecyclerView().smoothScrollToPosition(0)
    }

    override fun populateData(data: I) {
        refreshLayout?.isRefreshing = false
        (getRecyclerView().adapter as RecyclerViewAdapter).loadItems(data)
    }

    private fun initListFragmentComponent() {
        listFragmentComponent = DaggerListFragmentComponent.builder()
            .activityComponent(parentActivity.activityComponent)
            .fragmentModule(FragmentModule(this))
            .recyclerViewAdapterModule(RecyclerViewAdapterModule(this))
            .build()
    }

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getListSwipeRefreshView(): SwipeRefreshLayout?
}
