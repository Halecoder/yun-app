package com.hl.yun.chapter10

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Service中的代码都是默认运行在主线程当中的，
 * 如果直接在Service里处理一些耗时的逻辑，
 * 就很容易出现ANR（Application Not Responding）的情况。
 * 所以这个时候就需要用到  Android多线程编程的技术了，
 * 我们应该在Service的每个具体的方法里开启一个子线程，
 * 然后在这里处理那些耗时的逻辑。
 */

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        // 打印当前线程的id
        Log.d("MyIntentService", "Thread id is${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService", "onDestroy executed")
    }
}