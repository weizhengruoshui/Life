package com.stephen.combination.common.list.holder.view

import com.stephen.combination.common.AppListFragment
import com.stephen.combination.common.list.holder.viewmodel.ViewModelText
import com.stephen.combination.databinding.ViewHolderTextBinding
import com.yaya.data.viewholder.RecyclerViewTextItem
import javax.inject.Inject

class ViewHolderText(
    private val viewHolderTextBinding: ViewHolderTextBinding,
    parentFragmentApp: AppListFragment<*, *>
) : RecyclerViewHolder<RecyclerViewTextItem>(viewHolderTextBinding.root, parentFragmentApp) {

    @Inject
    lateinit var viewModelText: ViewModelText

    override fun bindData(data: RecyclerViewTextItem) {
        viewHolderComponent.inject(this)
        viewModelText.recyclerViewTextItem = data
        populateView()
    }

    private fun populateView() {
        viewModelText.apply {
            viewHolderTextBinding.bViewHolderContent.text = recyclerViewTextItem?.text
            viewHolderTextBinding.bViewHolderUpdateTime.text = recyclerViewTextItem?.updateTime
        }
    }

    override fun onViewRecycled() {

    }

}