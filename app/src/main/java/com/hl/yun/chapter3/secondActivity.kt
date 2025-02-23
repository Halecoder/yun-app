package com.hl.yun.chapter3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_second.*

class secondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 获取传递过来的数据
        val data = intent.getStringExtra("data_1")
        Log.d("secondActivity", "data_1 is : $data")
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

        // 传递返回数据
        button2_2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_2", "Hello FirstActivity! 我是secondActivity的数据")
            setResult(RESULT_OK, intent)
            finish() // 关闭Activity
        }


    }

    // 点击返回键
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data_2", "Hello FirstActivity! 我是secondActivity的数据")
        setResult(RESULT_OK, intent)
        finish() // 关闭Activity
    }
}