package com.stephen.combination.common.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.stephen.combination.common.AppListFragment
import com.stephen.combination.common.list.holder.view.LoadingViewHolder
import com.stephen.combination.common.list.holder.view.RecyclerViewHolder
import com.stephen.combination.databinding.ViewHolderLoadingBinding
import com.yaya.data.LoadingData

class LoadingItemWrapper : ItemWrapper<LoadingData> {
    override fun getViewHolder(
        parent: ViewGroup,
        parentFragmentApp: AppListFragment<*, *>
    ): RecyclerViewHolder<LoadingData> {
        return LoadingViewHolder(
            ViewHolderLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parentFragmentApp
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is LoadingData
    }
}