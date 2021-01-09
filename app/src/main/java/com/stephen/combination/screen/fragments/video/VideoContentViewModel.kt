package com.stephen.combination.screen.fragments.video

import com.stephen.combination.common.viewmodel.ListViewModel
import com.yaya.data.DataRepository
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem

class VideoContentViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewTextAndImageItem>>(dataRepository) {


    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun onLoadMore() {
        TODO("Not yet implemented")
    }

    override fun initializeLoadedData(): MutableList<RecyclerViewTextAndImageItem> {
        return mutableListOf()
    }
}