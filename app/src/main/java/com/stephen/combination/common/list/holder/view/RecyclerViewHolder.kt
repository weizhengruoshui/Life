package com.stephen.combination.common.list.holder.view

import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stephen.combination.dagger.component.ViewHolderComponent
import com.stephen.combination.common.AppListFragment
import com.stephen.combination.dagger.component.DaggerViewHolderComponent

/**
 * @property I item type
 */
abstract class RecyclerViewHolder<I : Parcelable>(
    itemView: View,
    parentFragmentApp: AppListFragment<*, *>
) :
    RecyclerView.ViewHolder(itemView) {

    var viewHolderComponent: ViewHolderComponent = DaggerViewHolderComponent.builder()
        .listFragmentComponent(parentFragmentApp.listFragmentComponent)
        .build()

    abstract fun bindData(data: I)

    abstract fun onViewRecycled()

    fun getString(id: Int): String {
        return itemView.resources.getString(id)
    }
}