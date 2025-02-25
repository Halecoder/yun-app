package com.hl.yun.chapter3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        //隐藏系统栏
        supportActionBar?.hide()
    }
}