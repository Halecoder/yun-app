package com.hl.yun.chapter3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_second.*

class secondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // 隐式调用浏览器 不同app进行跳转
        button2.setOnClickListener {
            // 隐式调用浏览器
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")
//            startActivity(intent)
            // 跳转到thirdactivity
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}