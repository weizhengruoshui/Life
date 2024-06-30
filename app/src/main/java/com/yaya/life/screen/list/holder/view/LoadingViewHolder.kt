package com.yaya.life.screen.list.holder.view

import com.yaya.life.basic.extension.visible
import com.yaya.life.databinding.ViewHolderLoadingBinding
import com.yaya.data.LoadingData

class LoadingViewHolder(
    private val viewBinding: ViewHolderLoadingBinding,
) : RecyclerViewHolder<LoadingData>(viewBinding.root) {

    override fun bindData(data: LoadingData) {
        viewBinding.loadingViewHolderProgressBar.visible()
    }

    override fun onViewRecycled() {
    }

}