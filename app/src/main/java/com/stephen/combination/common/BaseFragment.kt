package com.stephen.combination.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.stephen.combination.common.viewmodel.BaseViewModel
import com.stephen.combination.dagger.component.DaggerFragmentComponent
import com.stephen.combination.dagger.component.FragmentComponent
import com.stephen.combination.dagger.module.FragmentModule

/**
 * @property dataType is the fragment's base data.
 * @property viewModel is the fragment's viewModel
 */
abstract class BaseFragment<dataType, viewModel : BaseViewModel<dataType>> : Fragment() {

    val fragmentViewModel: viewModel by lazy {
        getViewModel()
    }

    lateinit var parentActivity: BaseActivity<*, *>

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

    fun provideFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
            .activityComponent((activity as BaseActivity<*, *>).activityComponent)
            .fragmentModule(
                FragmentModule(
                    this
                )
            )
            .build()
    }

    open fun bindObserver() {
        val observer: Observer<dataType> = Observer { data ->
            populateData(data)
        }
        fragmentViewModel.liveData.observe(this, observer)
    }

    open fun loadData() {
        fragmentViewModel.loadData()
    }

    open fun initVariables(savedInstanceState: Bundle?) {
        parentActivity = requireActivity() as BaseActivity<*, *>
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