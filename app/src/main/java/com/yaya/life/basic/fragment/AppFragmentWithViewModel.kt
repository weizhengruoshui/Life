package com.yaya.life.basic.fragment

import android.os.Bundle
import com.yaya.life.basic.viewmodel.BaseViewModel

abstract class AppFragmentWithViewModel<viewModel : BaseViewModel>: AppFragment() {

    val fragmentViewModel: viewModel by this.provideFragmentViewModel()

    abstract fun provideFragmentViewModel(): Lazy<viewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentViewModel.clearDisposables()
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentViewModel.disposeAllDisposables()
    }
}