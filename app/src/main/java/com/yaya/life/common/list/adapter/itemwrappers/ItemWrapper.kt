package com.yaya.life.common.list.adapter.itemwrappers

import android.os.Parcelable
import android.view.ViewGroup
import com.yaya.life.common.list.holder.view.RecyclerViewHolder

/**
 * @property I the item's type
 */
interface ItemWrapper<I : Parcelable> {

    fun getViewHolder(
        parent: ViewGroup,
    ): RecyclerViewHolder<I>

    fun isThisType(item: Any): Boolean
}