package com.stephen.combination.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.stephen.combination.common.viewmodel.BaseViewModel

/**
 * @property dataType is the fragment's base data.
 * @property viewModel is the fragment's viewModel
 */
abstract class AppFragment<dataType, viewModel : BaseViewModel<dataType>> :
    Fragment() {

    val fragmentViewModel: viewModel by lazy {
        getViewModel()
    }

    lateinit var parentActivity: AppActivity<*, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables(savedInstanceState)
        bindObserver()
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateRootView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attributeViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resetViewBinding()
    }

    override fun onDestroy() {
        fragmentViewModel.disposeAllDisposables()
        super.onDestroy()
    }

    open fun bindObserver() {
        val observer: Observer<dataType> = Observer { data ->
            populateData(data)
        }
        fragmentViewModel.data.observe(this, observer)
    }

    open fun loadData() {
        fragmentViewModel.loadData()
    }

    open fun initVariables(savedInstanceState: Bundle?) {
        parentActivity = requireActivity() as AppActivity<*, *>
    }

    abstract fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    abstract fun attributeViews()

    abstract fun populateData(data: dataType)

    abstract fun getViewModel(): viewModel

    abstract fun resetViewBinding()
}