package com.hl.yun.chapter10

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val tag = "MyService"
    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }

        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
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