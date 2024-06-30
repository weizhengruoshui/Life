package com.yaya.life.screen.list.adapter.itemwrappers

import android.os.Parcelable
import android.view.ViewGroup
import com.yaya.life.screen.list.holder.view.RecyclerViewHolder

/**
 * @property I the item's type
 */
interface ItemWrapper<I : Parcelable> {

    fun getViewHolder(
        parent: ViewGroup,
    ): RecyclerViewHolder<I>

    fun isThisType(item: Any): Boolean
}