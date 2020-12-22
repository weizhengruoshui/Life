package com.stephen.combination.notification

import com.stephen.combination.common.viewmodel.BaseViewModel

class NotificationViewModel : BaseViewModel<String>() {

    override fun loadData() {
        liveData.value = "load"
    }
}