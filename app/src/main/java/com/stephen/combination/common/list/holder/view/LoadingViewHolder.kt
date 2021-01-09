package com.stephen.combination.common.list.holder.view

import com.stephen.combination.common.AppListFragment
import com.stephen.combination.common.extension.visible
import com.stephen.combination.databinding.ViewHolderLoadingBinding
import com.yaya.data.LoadingData

class LoadingViewHolder(
    private val viewBinding: ViewHolderLoadingBinding,
    parentFragmentApp: AppListFragment<*, *>
) : RecyclerViewHolder<LoadingData>(viewBinding.root, parentFragmentApp) {

    override fun bindData(data: LoadingData) {
        viewBinding.loadingViewHolderProgressBar.visible()
    }

    override fun onViewRecycled() {
    }

}