package com.yaya.life.screen.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yaya.life.screen.list.holder.view.LoadingViewHolder
import com.yaya.life.screen.list.holder.view.RecyclerViewHolder
import com.yaya.life.databinding.ViewHolderLoadingBinding
import com.yaya.data.LoadingData

class LoadingItemWrapper : ItemWrapper<LoadingData> {
    override fun getViewHolder(
        parent: ViewGroup,
    ): RecyclerViewHolder<LoadingData> {
        return LoadingViewHolder(
            ViewHolderLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is LoadingData
    }
}