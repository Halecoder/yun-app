package com.hl.yun.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R

class BroadcastActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.broadcast)
        //动态注册广播
        timeChangeReceiver = TimeChangeReceiver()
        //registerReceiver(timeChangeReceiver, IntentFilter("android.intent.action.TIME_TICK"))
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