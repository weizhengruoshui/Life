package com.stephen.combination.common.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.stephen.combination.common.list.holder.view.ViewHolderText
import com.yaya.data.viewholder.RecyclerViewTextItem
import com.stephen.combination.databinding.ViewHolderTextBinding
import com.stephen.combination.common.AppListFragment

class TextItemWrapper :
    ItemWrapper<RecyclerViewTextItem> {
    override fun getViewHolder(
        parent: ViewGroup,
        parentFragmentApp: AppListFragment<*, *>
    ): ViewHolderText {
        return ViewHolderText(
            ViewHolderTextBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parentFragmentApp
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is RecyclerViewTextItem
    }
}