package com.yaya.life.screen.list.holder.view

import com.yaya.life.databinding.ViewHolderTextBinding
import com.yaya.data.viewholderitems.RecyclerViewTextItem

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