package com.hl.yun.chapter13

import androidx.lifecycle.ViewModel

//class CountViewModel : ViewModel() {
//    var counter = 0
//}

//所有与界面相关的数据都放在ViewModel中
class CountViewModel(countReserved: Int) : ViewModel() {
    var counter = countReserved
}