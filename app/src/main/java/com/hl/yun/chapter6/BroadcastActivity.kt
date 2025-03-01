package com.hl.yun.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import kotlinx.android.synthetic.main.broadcast.*

class BroadcastActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.broadcast)
        //动态注册广播
        //timeChangeReceiver = TimeChangeReceiver()
        //registerReceiver(timeChangeReceiver, IntentFilter("android.intent.action.TIME_TICK"))

        // 点击按钮发送一个广播
        mybroad.setOnClickListener {
            val intent = Intent("com.hl.yun.chapter6.ACTION_MY_BROADCAST")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0, "时间改变了", Toast.LENGTH_SHORT).show()
        }

    }
}