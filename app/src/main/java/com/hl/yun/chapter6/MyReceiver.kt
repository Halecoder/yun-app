package com.hl.yun.chapter6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        //Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show()
        //接受自定义广播
        if (intent.action == "com.hl.yun.chapter6.ACTION_MY_BROADCAST") {
            Toast.makeText(context, "接收到自定义广播", Toast.LENGTH_LONG).show()
        }
    }
}