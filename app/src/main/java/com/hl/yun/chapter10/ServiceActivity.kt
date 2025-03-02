package com.hl.yun.chapter10

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {
    lateinit var downloadBinder: MyService.DownloadBinder

    // MyService可以和多个Activity进行绑定，而且在绑定完成后，
    // 它们都可以获取相同的DownloadBinder实例
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName,
            service: IBinder
        ) { //Activity与Service成功绑定的时候调用
            downloadBinder = service as MyService.DownloadBinder //向下转型
            //从这里开始可以从Activity中调用Service中的方法
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            //在Service的创建进程崩溃或者被杀掉的时候
        }
    } // 匿名内部类

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent) // 启动Service
        }
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent) // 停止Service
        }

        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            // //第三个参数则是一个标志位， 这里传入BIND_AUTO_CREATE
            //表示在Activity和Service进行绑定后自动创建Service。这会使得
            //MyService中的onCreate()方法得到执行， 但onStartCommand()方法不会执行
            //也就是说这种的一绑定的话就会自动 create Service
            bindService(intent, connection, BIND_AUTO_CREATE) // 绑定Service
        }
        unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
    }
}