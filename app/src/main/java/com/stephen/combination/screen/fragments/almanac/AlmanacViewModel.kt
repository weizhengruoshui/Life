package com.stephen.combination.screen.fragments.almanac

import com.yaya.data.DataRepository
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.stephen.combination.common.viewmodel.BaseViewModel
import com.yaya.utils.DateUtils
import io.reactivex.rxkotlin.Singles
import java.util.*

class AlmanacViewModel(private val dataRepository: DataRepository) :
    BaseViewModel<Pair<AlmanacDay?, MutableList<AlmanacHour>?>>() {

    override fun loadData() {
        val date = Calendar.getInstance().time
        compositeDisposable.add(
            Singles.zip(
                dataRepository.getAlmanacDay(DateUtils.formatDate(date)),
                dataRepository.getAlmanacHours(DateUtils.formatDate(date))
            ).subscribe({ (almanacDayResponse, almanacHoursResponse) ->
                liveData.value =
                    Pair(almanacDayResponse.body()?.result, almanacHoursResponse.body()?.result)
            }, ::handleException)
        )
    }
}