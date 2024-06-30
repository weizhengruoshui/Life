package com.yaya.life.screen.list.adapter.itemwrappers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yaya.life.screen.list.holder.view.ViewHolderTextAndImage
import com.yaya.data.viewholderitems.RecyclerViewTextAndImageItem
import com.yaya.life.databinding.ViewHolderImageBinding

class TextAndImageItemWrapper :
    ItemWrapper<RecyclerViewTextAndImageItem> {

    override fun getViewHolder(
        parent: ViewGroup,
    ): ViewHolderTextAndImage {
        return ViewHolderTextAndImage(
            ViewHolderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun isThisType(item: Any): Boolean {
        return item is RecyclerViewTextAndImageItem
    }

}