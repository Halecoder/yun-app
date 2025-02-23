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

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("data", "onCreate execute")
//        button1.setOnClickListener {
//            Toast.makeText(this, "button1", Toast.LENGTH_LONG).show()
//        }

//        点击跳转第二个activity google推荐
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.button1.setOnClickListener {
            val intent = Intent(this, secondActivity::class.java)
            startActivity(intent)
        }
//        隐式调用activity
//        button1.setOnClickListener {
//            val intent = Intent("com.example.activitytest.ACTION_START")
//            intent.addCategory("com.example.activitytest.MY_CATEGORY")
//            startActivity(intent)
//        }
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
