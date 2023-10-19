package com.yaya.life.common.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yaya.life.common.list.holder.view.ViewHolderText
import com.yaya.life.databinding.ViewHolderTextBinding
import com.yaya.data.viewholder.RecyclerViewTextItem

class TextItemWrapper :
    ItemWrapper<RecyclerViewTextItem> {
    override fun getViewHolder(
        parent: ViewGroup,
    ): ViewHolderText {
        return ViewHolderText(
            ViewHolderTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is RecyclerViewTextItem
    }
}