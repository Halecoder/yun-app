package com.hl.yun.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OtherReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent.action == "com.hl.yun.chapter6.ACTION_MY_BROADCAST") {
            Toast.makeText(context, "接收到广播从OtherReceiver", Toast.LENGTH_SHORT).show()
            // 定义了other比my的高，就可以从此处截断
            abortBroadcast()
        }
    }
}