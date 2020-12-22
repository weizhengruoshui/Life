package com.stephen.combination.common.list.adapter

import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stephen.combination.common.list.adapter.itemwrappers.ItemWrapper
import com.stephen.combination.common.list.adapter.itemwrappers.TextAndImageItemWrapper
import com.stephen.combination.common.list.adapter.itemwrappers.TextItemWrapper
import com.stephen.combination.common.list.adapter.itemwrappers.VideoItemWrapper
import com.stephen.combination.common.list.holder.view.RecyclerViewHolder
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.yaya.data.viewholder.RecyclerViewTextItem
import com.stephen.combination.common.AppListFragment


open class RecyclerViewAdapter(private val parentFragmentApp: AppListFragment<*, *>) :
    RecyclerView.Adapter<RecyclerViewHolder<Parcelable>>() {

    private val items = mutableListOf<Parcelable>()
    private val viewHolderManager = ViewHolderManager()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder<Parcelable> {
        return viewHolderManager.getViewHolder(parent, viewType, parentFragmentApp)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder<Parcelable>, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return viewHolderManager.getItemViewType(items[position])
    }

    override fun onViewRecycled(holder: RecyclerViewHolder<Parcelable>) {
        holder.onViewRecycled()
    }

    fun addItems(additionalItems: MutableList<out Parcelable>) {
        val newItems = items.plus(additionalItems).toMutableList()
        val diffResult = DiffUtil.calculateDiff(DiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun loadItems(newItems: MutableList<out Parcelable>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffCallback(
        private val newData: MutableList<out Parcelable>,
        private val oldData: MutableList<out Parcelable>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldData[oldItemPosition]
            val newItem = newData[newItemPosition]
            if (newItem is RecyclerViewTextItem) {
                if (oldItem !is RecyclerViewTextItem) {
                    return true
                }
                return newItem.text == oldItem.text
            }

            if (newItem is RecyclerViewTextAndImageItem) {
                if (oldItem !is RecyclerViewTextAndImageItem) {
                    return true
                }
                return newItem.link == oldItem.link
            }

            return false
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return true
        }

    }

    @Suppress("UNCHECKED_CAST")
    class ViewHolderManager {
        private val itemWrappers =
            SparseArray<ItemWrapper<Parcelable>>()
        private val imageType = 0
        private val textType = 1
        private val videoType = 2

        init {
            itemWrappers.put(
                imageType,
                TextAndImageItemWrapper() as ItemWrapper<Parcelable>
            )
            itemWrappers.put(
                textType,
                TextItemWrapper() as ItemWrapper<Parcelable>
            )
            itemWrappers.put(
                videoType,
                VideoItemWrapper() as ItemWrapper<Parcelable>
            )
        }

        fun getViewHolder(
            parent: ViewGroup,
            viewType: Int,
            parentFragmentApp: AppListFragment<*, *>
        ): RecyclerViewHolder<Parcelable> {
            return itemWrappers.valueAt(viewType).getViewHolder(parent, parentFragmentApp)
        }

        fun getItemViewType(item: Any): Int {
            for (i in 0 until itemWrappers.size()) {
                val vieType = itemWrappers.keyAt(i)
                val itemWrapper = itemWrappers.valueAt(i)

                if (itemWrapper.isThisType(item)) {
                    return vieType
                }
            }

            throw RuntimeException("Didn't find this type")
        }
    }
}

