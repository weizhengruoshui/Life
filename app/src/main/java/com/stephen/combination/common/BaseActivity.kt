package com.stephen.combination.common

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.stephen.combination.MainApplication
import com.stephen.combination.common.viewmodel.BaseViewModel
import com.stephen.combination.dagger.component.ActivityComponent
import com.stephen.combination.dagger.component.DaggerActivityComponent
import com.stephen.combination.dagger.module.ActivityModule

/**
 * @property dataType is the activity's base data type
 * @property viewModel  is the activity's viewModel
 */

@SuppressLint("Registered")
abstract class BaseActivity<dataType, viewModel : BaseViewModel<dataType>> : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    private val activityViewModel: viewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        initVariables()
        bindObserver()
        loadData()
        attributeViews()
    }

    open fun bindObserver() {
        val observer: Observer<dataType> = Observer { data ->
            populateData(data)
        }
        activityViewModel.liveData.observe(this, observer)
    }

    /*
    load data from network or local
     */
    open fun loadData() {
        activityViewModel.loadData()
    }

    override fun onDestroy() {
        activityViewModel.disposeAllDisposables()
        super.onDestroy()
    }

    open fun initComponent() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as MainApplication).appComponent)
            .activityModule(
                ActivityModule(
                    this
                )
            )
            .build()
    }

    abstract fun initVariables()

    abstract fun attributeViews()

    abstract fun populateData(data: dataType)

    abstract fun getViewModel(): viewModel
}
