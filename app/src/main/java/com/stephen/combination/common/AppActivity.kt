package com.stephen.combination.common

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.stephen.combination.common.viewmodel.BaseViewModel

/**
 * @property dataType is the activity's base data type
 * @property viewModel  is the activity's viewModel
 */

@SuppressLint("Registered")
abstract class AppActivity<dataType, viewModel : BaseViewModel<dataType>> : AppCompatActivity() {


    private val activityViewModel: viewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
        bindObserver()
        loadData()
        attributeViews()
    }

    open fun bindObserver() {
        val observer: Observer<dataType> = Observer { data ->
            populateData(data)
        }
        activityViewModel.data.observe(this, observer)
    }

    /*
    load data from network or local
     */
    open fun loadData() {
        activityViewModel.loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        activityViewModel.disposeAllDisposables()
        super.onDestroy()
    }

    open fun initVariables() {
        // implemented by child
    }

    abstract fun attributeViews()

    abstract fun populateData(data: dataType)

    abstract fun getViewModel(): viewModel
}
