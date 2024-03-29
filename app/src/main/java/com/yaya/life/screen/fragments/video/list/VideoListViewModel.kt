package com.yaya.life.screen.fragments.video.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaya.life.common.list.holder.view.ViewHolderVideo
import com.yaya.life.common.viewmodel.ListViewModel
import com.yaya.data.DataRepository
import com.yaya.data.DataWithList
import com.yaya.data.viewholder.RecyclerViewVideoItem
import com.yaya.utils.LogUtils
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class VideoListViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewVideoItem>>(dataRepository), LifecycleObserver {

    var playFirstVideoSubscribe: Disposable? = null
    var currentPlayItemPosition = -1
    var shouldRunResume = false

    override fun loadData() {
        compositeDisposable.add(
            dataRepository.getVideos()
                .subscribe(::handleVideosData)
        )
    }

    override fun onLoadMore() {
        //TODO
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        shouldRunResume = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        shouldRunResume = true
        unbindFirstPlayVideoSchedule()
    }

    fun unbindFirstPlayVideoSchedule() {
        playFirstVideoSubscribe?.apply {
            compositeDisposable.remove(this)
        }
    }

    fun firstPlayViewHolder(recyclerView: RecyclerView) {
        if (data.value?.isEmpty() == true) {
            return
        }
        val playerFirstVideoSingle = Single.create(SingleOnSubscribe<String>() { emitter ->
            while (recyclerView.run {
                    findViewHolderForAdapterPosition(
                        (layoutManager as LinearLayoutManager)
                            .findFirstCompletelyVisibleItemPosition()
                    ) == null
                }) {
                LogUtils.logD(javaClass.simpleName, "list hasn't finished inflating")
            }
            emitter.onSuccess("ready")
        })

        playFirstVideoSubscribe = playerFirstVideoSingle
            .subscribeOn(Schedulers.computation())
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .ignoreElement()
            .subscribe {
                playTheTargetPositionItem(recyclerView,
                    recyclerView.run {
                        (layoutManager as LinearLayoutManager)
                            .findFirstCompletelyVisibleItemPosition()
                    })
            }
        compositeDisposable.add(playFirstVideoSubscribe!!)
    }

    fun playTheFirstCompletelyShowViewHolder(recyclerView: RecyclerView) {
        val firstCompletelyVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        if (firstCompletelyVisibleItemPosition == currentPlayItemPosition) {
            return
        }
        stopTheTargetPositionItem(recyclerView, currentPlayItemPosition)
        playTheTargetPositionItem(recyclerView, firstCompletelyVisibleItemPosition)
    }

    fun stopPlayingViewHolder(recyclerView: RecyclerView) {
        stopTheTargetPositionItem(recyclerView, currentPlayItemPosition)
    }

    override fun initializeLoadedData(): MutableList<RecyclerViewVideoItem> {
        return mutableListOf()
    }

    private fun playTheTargetPositionItem(recyclerView: RecyclerView, position: Int) {
        currentPlayItemPosition = position
        (recyclerView.findViewHolderForAdapterPosition(position) as? ViewHolderVideo)?.playVideo()
    }

    private fun stopTheTargetPositionItem(recyclerView: RecyclerView, position: Int) {
        currentPlayItemPosition = -1
        (recyclerView.findViewHolderForAdapterPosition(position) as? ViewHolderVideo)?.stopVideo()
    }

    private fun handleVideosData(videosResponse: DataWithList<RecyclerViewVideoItem>) {
        data.value = videosResponse.result
    }
}