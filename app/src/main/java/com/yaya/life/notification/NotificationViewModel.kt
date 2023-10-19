package com.yaya.life.notification

import com.yaya.life.common.viewmodel.BaseViewModel

class NotificationViewModel : BaseViewModel<String>() {

    override fun loadData() {
        data.value = "load"
    }
}