package com.stephen.combination.screen.fragments.jokes

import com.stephen.combination.common.viewmodel.ListViewModel
import com.yaya.data.DataRepository
import com.yaya.data.DataWithObject
import com.yaya.data.jokes.JokesResult
import com.yaya.data.viewholder.RecyclerViewTextItem
import com.yaya.utils.LogUtils

class JokesViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewTextItem>>(
        dataRepository
    ) {

    override fun loadData() {
        resetParams()
        compositeDisposable.add(
            dataRepository.getLatestJokes(page, pageSize)
                .subscribe(::handleLatestJokesData)
        )
    }

    override fun onLoadMore() {
        LogUtils.logD(javaClass.simpleName, "onLoadMore")
        compositeDisposable.add(
            dataRepository.getLatestJokes(page, pageSize)
                .subscribe(::handleLoadMoreJokesData)
        )
    }

    private fun handleLatestJokesData(jokesResponse: DataWithObject<JokesResult>) {
        data.value = jokesResponse.result.data.map {
            RecyclerViewTextItem(it.content, it.updatetime)
        }.toMutableList()
    }

    private fun handleLoadMoreJokesData(jokesResponse: DataWithObject<JokesResult>) {
        moreData.value = jokesResponse.result.data.map {
            RecyclerViewTextItem(it.content, it.updatetime)
        }.toMutableList()
    }

    override fun initializeLoadedData(): MutableList<RecyclerViewTextItem> {
        return mutableListOf()
    }
}