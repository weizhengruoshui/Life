package com.yaya.life.screen.list.holder.view

import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @property I item type
 */
abstract class RecyclerViewHolder<I : Parcelable>(
    itemView: View,
) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bindData(data: I)

    abstract fun onViewRecycled()

    fun getString(id: Int): String {
        return itemView.resources.getString(id)
    }
}