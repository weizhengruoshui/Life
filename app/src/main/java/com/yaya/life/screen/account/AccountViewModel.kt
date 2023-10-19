package com.yaya.life.screen.account

import com.yaya.life.common.viewmodel.BaseViewModel

class AccountViewModel: BaseViewModel<String>() {

    override fun loadData() {
        data.value = "Account Page"
    }
}