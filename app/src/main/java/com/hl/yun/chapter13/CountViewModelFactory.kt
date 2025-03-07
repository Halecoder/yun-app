package com.hl.yun.chapter13

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountViewModel(countReserved) as T
        //这里可以创建CountViewModel实例， 因为这里的create方法执行实际和Activity的生命周期无关
    }
}