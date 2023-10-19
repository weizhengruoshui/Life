package com.yaya.life.screen.fragments.video

import com.yaya.life.common.viewmodel.ListViewModel
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