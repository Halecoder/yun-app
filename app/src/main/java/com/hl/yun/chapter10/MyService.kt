package com.hl.yun.chapter10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.hl.yun.R

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

        //前台图标类似于Lspose通知栏
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "my_service", "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(this, FrontServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.drawable.small_icon)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.large_icon
                )
            )
            .setContentIntent(pi)
            .build()
        //上面都是通知的套路， 就这里改了一下子
        startForeground(1, notification)
    }

    //启动Service时候调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "onStartCommanding") //这个必须在上面
        Thread {
            // 停止服务
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    //销毁Service时候调用
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestoring")
    }
}