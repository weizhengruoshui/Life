package com.stephen.combination.common.list.adapter.itemwrappers

import android.os.Parcelable
import android.view.ViewGroup
import com.stephen.combination.common.list.holder.view.RecyclerViewHolder
import com.stephen.combination.common.AppListFragment

/**
 * @property I the item's type
 */
interface ItemWrapper<I : Parcelable> {

    fun getViewHolder(
        parent: ViewGroup,
        parentFragmentApp: AppListFragment<*, *>
    ): RecyclerViewHolder<I>

    fun isThisType(item: Any): Boolean
}