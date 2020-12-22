package com.stephen.combination.screen.fragments.jokes

import com.yaya.data.DataRepository
import com.yaya.data.DataWithList
import com.yaya.data.jokes.JokeDetail
import com.yaya.data.viewholder.RecyclerViewTextItem
import com.stephen.combination.common.viewmodel.ListViewModel

class JokesViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewTextItem>>(
        dataRepository
    ) {

    override fun loadData() {
        compositeDisposable.add(
            dataRepository.getRandomJokes()
                .subscribe(::handleJokesData)
        )
    }

    private fun handleJokesData(jokesResponse: DataWithList<JokeDetail>) {
        liveData.value = jokesResponse.result.map {
            RecyclerViewTextItem(it.content, it.updatetime)
        }.toMutableList()
    }

    override fun loadMore() {
        TODO("Not yet implemented")
    }
}