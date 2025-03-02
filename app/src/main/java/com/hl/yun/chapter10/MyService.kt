package com.hl.yun.chapter10

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val tag = "MyService"
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    //第一次创建Service执行
    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "onCreating")
    }

    //启动Service时候调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "onStartCommanding") //这个必须在上面
        return super.onStartCommand(intent, flags, startId)
    }

    //销毁Service时候调用
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestoring")
    }
}