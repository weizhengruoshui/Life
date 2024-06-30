package com.yaya.life.basic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yaya.data.DataRepository

class DataViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DataRepository::class.java).newInstance(dataRepository)
    }
}