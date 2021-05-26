package com.stephen.combination.screen.account

import com.stephen.combination.common.viewmodel.BaseViewModel

class AccountViewModel: BaseViewModel<String>() {

    override fun loadData() {
        data.value = "Account Page"
    }
}