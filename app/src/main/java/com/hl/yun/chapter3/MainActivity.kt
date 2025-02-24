package com.hl.yun.chapter3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import com.hl.yun.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding


    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_main)
    //    Log.d("data", "onCreate execute")
    //
    //    // 新版本启用数据返回方式 返回时的回调函数：second activity 返回时，给first activity传递数据 观察者
    //    //        private val launcher =
    //    //            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    //    //                if (it.resultCode == RESULT_OK)
    //    //                    Log.d(
    //    //                        "FirstActivity",
    //    //                        "returned data is ${it.data?.getStringExtra("return_data")}"
    //    //                    )
    //    //                else Log.d("FirstActivity", "there isn't returned data")
    //    //            }
    //    //
    //    //        launcher.launch(Intent(this, secondActivity::class.java)) 观察者启动第二个activity活动界面
    //
    //
    //    //        button1.setOnClickListener {
    //    //            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show()
    //    //        }
    //
    //    //       显式调用点击跳转第二个activity google推荐
    //    //        mainBinding = ActivityMainBinding.inflate(layoutInflater)
    //    //        setContentView(mainBinding.root)
    //    //        mainBinding.button1.setOnClickListener {
    //    //            val intent = Intent(this, secondActivity::class.java)
    //    //            startActivity(intent)
    //    //        }
    //
    //
    //    //        隐式调用activity
    //    //        button1.setOnClickListener {
    //    //            val intent = Intent("com.example.activitytest.ACTION_START")
    //    //            intent.addCategory("com.example.activitytest.MY_CATEGORY")
    //    //            startActivity(intent)
    //    //        }
    //
    //    // 数据传输到第二个activity
    //    //button1.setOnClickListener {
    //    //    val intent = Intent(this, secondActivity::class.java)
    //    //    intent.putExtra("data_1", "Hello, secondActivity")
    //    //    //startActivity(intent)
    //    //    startActivityForResult(intent, 1) // 启动activity并返回数据(旧版本)
    //    //}
    //
    //
    //
    //}

    // 生存期测试
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_main)

        startNormalActivity.setOnClickListener {
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }
        startDialogActivity.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        //回收后获取保存的数据
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, "tempData is $tempData")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }


    // activity被回收时触发
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }


    //旧版本获取secondActivity返回数据
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val data_2 = data?.getStringExtra("data_2")
                Log.d("Hello FirstActivity", "secondActivity returned data is ${data_2}")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}
