package com.stephen.combination.common.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.stephen.combination.common.list.holder.view.ViewHolderVideo
import com.yaya.data.viewholder.RecyclerViewVideoItem
import com.stephen.combination.databinding.ViewHolderVideoBinding
import com.stephen.combination.common.AppListFragment

class VideoItemWrapper : ItemWrapper<RecyclerViewVideoItem> {
    override fun getViewHolder(
        parent: ViewGroup,
        parentFragmentApp: AppListFragment<*, *>
    ): ViewHolderVideo {
        return ViewHolderVideo(
            ViewHolderVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parentFragmentApp
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is RecyclerViewVideoItem
    }
}