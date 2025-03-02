package com.hl.yun.chapter8

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_call.*

/**
 * Android 6.0开始在使用危险权限时必须进行运行时权限处理
 */
class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        makeCall.setOnClickListener {
            //判断是否获取目标权限
            //判断是否拿到某权限接收两个参数：第一个Context，第二个具体的权限名
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //没有就申请权限 向用户显示申请权限
                //接收3个参数：第一个参数要求是Activity的实例（在哪一个Activity中申请权限）；
                // 第二个参数是一个String数组，里面是申请的权限名；
                // 第三个参数是请求码，要求是唯一值
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE), 1
                )
            } else {
                //有就直接执行
                call()
            }
        }
    }

    //处理请求权限结果
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    call()
                } else {
                    Toast.makeText(
                        this, "You denied the permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}