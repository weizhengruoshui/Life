package com.stephen.combination.common.list.holder.view

import com.stephen.combination.common.AppListFragment
import com.stephen.combination.databinding.ViewHolderTextBinding
import com.yaya.data.viewholder.RecyclerViewTextItem

class ViewHolderText(
    private val viewHolderTextBinding: ViewHolderTextBinding,
) : RecyclerViewHolder<RecyclerViewTextItem>(viewHolderTextBinding.root) {


    override fun bindData(data: RecyclerViewTextItem) {
        populateView(data)
    }

    private fun populateView(data: RecyclerViewTextItem) {
        viewHolderTextBinding.textViewHolderContent.text = data.text
        viewHolderTextBinding.textViewHolderUpdateTime.text = data.updateTime
    }

    override fun onViewRecycled() {

    }

}