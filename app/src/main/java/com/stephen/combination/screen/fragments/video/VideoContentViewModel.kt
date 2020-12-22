package com.stephen.combination.screen.fragments.video

import com.yaya.data.DataRepository
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.stephen.combination.common.viewmodel.ListViewModel

class VideoContentViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewTextAndImageItem>>(dataRepository) {
    override fun loadMore() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        //to do
    }
}