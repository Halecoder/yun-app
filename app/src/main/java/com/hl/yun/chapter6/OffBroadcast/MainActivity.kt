package com.hl.yun.chapter6.OffBroadcast

import BaseActivity
import android.content.Intent
import android.os.Bundle
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        forceOffline.setOnClickListener {
            val intent = Intent("com.hl.yun.chapter6.OffBroadcast.FORCE_OFFLINE")
            sendBroadcast(intent)
        }

    }
}