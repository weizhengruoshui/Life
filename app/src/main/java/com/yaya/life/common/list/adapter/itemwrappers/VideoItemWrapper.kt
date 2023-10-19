package com.yaya.life.common.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yaya.life.common.list.holder.view.ViewHolderVideo
import com.yaya.life.databinding.ViewHolderVideoBinding
import com.yaya.data.viewholder.RecyclerViewVideoItem

class VideoItemWrapper : ItemWrapper<RecyclerViewVideoItem> {
    override fun getViewHolder(
        parent: ViewGroup
    ): ViewHolderVideo {
        return ViewHolderVideo(
            ViewHolderVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is RecyclerViewVideoItem
    }
}