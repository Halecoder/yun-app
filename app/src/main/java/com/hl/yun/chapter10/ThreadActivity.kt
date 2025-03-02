package com.hl.yun.chapter10

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_thread.*
import kotlin.concurrent.thread

/**
 * android线程不安全,不能在主线程中修改UI,使用异步消息处理机制解决
 * service主要用途在于不像activity需要与用户操作,service运行在后台,可以多线程
 * 组成：Message、Handler、MessageQueue 和 Looper。
1.Message
线程之间传递的消息，内部携带少量的信息，用于在不同线程之间传递数据
Message的what字段，除此之外还可以使用arg1和arg2字段来携带一些整型数据，使用obj字段携带一个Object对象。
2.Handler
Handler顾名思义也就是处理者的意思，它主要是用于发送和处理消息的。发送消息一般是使用
Handler的sendMessage()方法、post()方法等，而发出的消息经过一系列地辗转处理后，最终会传递到Handler的handleMessage()方法中。
3.MessageQueue
MessageQueue 是消息队列的意思，它主要用于存放所有通过Handler发送的消息。这部分消息会一直存在于消息队列中，等待被处理。每个线程中只会有一 MessageQueue对象。
4.Looper
Looper 是每个线程中的 MessageQueue 的管家，调用 Looper 的 loop() 方法后，就会进入一个无限循环当中，然后每当发现MessageQueue中存在一条消息时，就会将它取出，并传递到 Handler的handleMessage()方法中。每个线程中只会有一个Looper对象。
 */
class ThreadActivity : AppCompatActivity() {
    val updateText = 1
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                //在这里进行UI操作
                updateText -> findViewById<TextView>(R.id.textView).text =
                    "你好，世界。"
            }
        }
    }

    //异步消息处理流程：首先需要在主线程当中创建一个Handler对象，并重写handleMessage()方法。
    // 然后当子线程中需要进行UI操作时，就创建一个Message对象，并通过Handler将这条消息发送出去。
    // 之后这条消息会被添加到MessageQueue的队列中等待被处理，
    // 而Looper则会一直尝试从MessageQueue中取出待处理消息，
    // 最后分发回Handler的handleMessage()方法中。
    // 由于Handler的构造函数中我们传入了Looper.getMainLooper()，
    // 所以此时handleMessage()方法中的代码也会在主线程中运行，
    // 于是我们在这里就可以安心地进行UI操作了

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        //用异步消息处理机制
        changeTextBtn.setOnClickListener {
            thread {
                //这里面就是子线程啦
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg) //将Message对象发送出去
            }
        }
    }
}