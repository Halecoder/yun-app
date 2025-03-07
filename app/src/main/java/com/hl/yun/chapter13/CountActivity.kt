package com.hl.yun.chapter13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_count.*

/**
 * 编程规范：给每一个 Activity 和 Fragment 都创建一个对应的 ViewModel
 */
class CountActivity : AppCompatActivity() {
    lateinit var viewModel: CountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        viewModel = ViewModelProvider(this).get(CountViewModel::class.java)
        plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        refreshCounter()
    }

    //发现的问题：Activity要负责处理逻辑，控制UI，处理网络请求……任务太重
    //ViewModel：分担Activity一部分工作，专门放和界面相关数据
    //界面上能看到的数据，相关变量都放ViewModel中。因为屏幕旋转时Activity重新创建，数据会丢失，但是ViewModel不会。
    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }
}