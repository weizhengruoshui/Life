package com.stephen.combination.common.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.stephen.combination.common.list.holder.view.ViewHolderTextAndImage
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.stephen.combination.databinding.ViewHolderImageBinding
import com.stephen.combination.common.AppListFragment

class TextAndImageItemWrapper :
    ItemWrapper<RecyclerViewTextAndImageItem> {

    override fun getViewHolder(
        parent: ViewGroup,
        parentFragmentApp: AppListFragment<*, *>
    ): ViewHolderTextAndImage {
        return ViewHolderTextAndImage(
            ViewHolderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parentFragmentApp
        )
    }

    override fun isThisType(item: Any): Boolean {
        return item is RecyclerViewTextAndImageItem
    }

}