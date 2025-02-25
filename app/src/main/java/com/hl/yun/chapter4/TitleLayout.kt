package com.hl.yun.chapter4

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.hl.yun.R
import kotlinx.android.synthetic.main.title.view.*

//自定义控件
class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title, this) // 引入title.xml布局文件
        //给标题栏中的按钮添加点击事件
        titleBack.setOnClickListener {
            /**
             * 注意， TitleLayout中接收的context参数实际上是一个Activity的实例， 在返回按钮的点击事
            件里， 我们要先将它转换成Activity类型， 然后再调用
            finish()方法销毁当前的Activity。
            Kotlin中的类型强制转换使用的关键字是as， 由于是第一次用到， 所以这里单独讲解一下
             */
            val activity = context as Activity
            activity.finish()
        }
        titleEdit.setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * 这里我们在TitleLayout的主构造函数中声明了Context和 AttributeSet这两个参数， 在布局中
    引入TitleLayout控件时就会调用这个构造函数。然后在init结
    构体中需要对标题栏布局进行动
    态加载， 这就要借助LayoutInflater来实现了。通过
    LayoutInflater的from()方法可以构建出
    — 个LayoutInflater对象， 然后调用inflate()方法就可以动态加载一个布局文件。
    inflate()方法接收两个参数： 第一个参数是要加载的布局文件的id， 这里我们传入
    R.layout.title； 第二个参数是给加载好的布局再添加一个父布局， 这里我们想要指定为
    TitleLayout， 于是直接传入this
     */
}