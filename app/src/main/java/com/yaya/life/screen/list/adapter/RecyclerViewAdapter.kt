package com.yaya.life.screen.list.adapter

import android.os.Parcelable
import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yaya.data.LoadingData
import com.yaya.data.viewholderitems.RecyclerViewTextAndImageItem
import com.yaya.data.viewholderitems.RecyclerViewTextItem
import com.yaya.life.screen.list.adapter.itemwrappers.ItemWrapper
import com.yaya.life.screen.list.adapter.itemwrappers.LoadingItemWrapper
import com.yaya.life.screen.list.adapter.itemwrappers.TextAndImageItemWrapper
import com.yaya.life.screen.list.adapter.itemwrappers.TextItemWrapper
import com.yaya.life.screen.list.holder.view.RecyclerViewHolder
import com.yaya.utils.LogUtils


class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewHolder<Parcelable>>() {

    private val dateList = mutableListOf<Parcelable>()
    private val viewHolderManager = ViewHolderManager()

    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                LogUtils.logD(javaClass.simpleName, "onChanged")
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                LogUtils.logD(
                    javaClass.simpleName,
                    "onItemRangeChanged($positionStart: positionStart, $itemCount: itemCount)"
                )
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                LogUtils.logD(
                    javaClass.simpleName,
                    "onItemRangeChanged($positionStart: positionStart, $itemCount: itemCount, $payload: payload?)"
                )
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                LogUtils.logD(
                    javaClass.simpleName,
                    "onItemRangeInserted($positionStart: positionStart, $itemCount: itemCount)"
                )
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                LogUtils.logD(
                    javaClass.simpleName,
                    "onItemRangeRemoved($positionStart: positionStart, $itemCount: itemCount)"
                )
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                LogUtils.logD(
                    javaClass.simpleName,
                    "onItemRangeMoved($fromPosition: fromPosition, $toPosition: toPosition, $itemCount: itemCount)"
                )
            }
        })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder<Parcelable> {
        return viewHolderManager.getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int = dateList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder<Parcelable>, position: Int) {
        holder.bindData(dateList[position])
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder<Parcelable>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            LogUtils.logD(javaClass.simpleName, "onBindViewHolder with payloads")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewHolderManager.getItemViewType(dateList[position])
    }

    override fun onViewRecycled(holder: RecyclerViewHolder<Parcelable>) {
        holder.onViewRecycled()
    }

    fun inflateData(newItems: List<Parcelable>) {
        dateList.clear()
        dateList.addAll(newItems)
        notifyDataSetChanged()
    }

    fun loadMoreItems(newItems: MutableList<Parcelable>) {
        val startPosition = dateList.size
        dateList.addAll(newItems)
        notifyItemRangeInserted(startPosition, dateList.size - startPosition)
    }

    @Suppress("UNCHECKED_CAST")
    class ViewHolderManager {
        private val itemWrappers =
            SparseArray<ItemWrapper<Parcelable>>()
        private val imageType = 0
        private val textType = 1
        private val loadingType = 2

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
                loadingType,
                LoadingItemWrapper() as ItemWrapper<Parcelable>
            )
        }

        fun getViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerViewHolder<Parcelable> {
            return itemWrappers.valueAt(viewType).getViewHolder(parent)
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

    class DiffItemCallback() : DiffUtil.ItemCallback<Parcelable>() {

        override fun areItemsTheSame(oldItem: Parcelable, newItem: Parcelable): Boolean {
            return when {
                oldItem is RecyclerViewTextItem && newItem is RecyclerViewTextItem -> true
                oldItem is LoadingData && newItem is LoadingData -> true
                oldItem is RecyclerViewTextAndImageItem && newItem is RecyclerViewTextAndImageItem -> true
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Parcelable, newItem: Parcelable): Boolean {
            return when {
                oldItem is RecyclerViewTextItem && newItem is RecyclerViewTextItem -> oldItem.text == newItem.text
                oldItem is LoadingData && newItem is LoadingData -> true
                oldItem is RecyclerViewTextAndImageItem && newItem is RecyclerViewTextAndImageItem -> oldItem.link == newItem.link
                else -> false
            }
        }

        override fun getChangePayload(oldItem: Parcelable, newItem: Parcelable): Any? {
            return super.getChangePayload(oldItem, newItem)
        }

    }
}

