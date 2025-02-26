package com.hl.yun.chapter5

import AnotherRightFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hl.yun.R
import kotlinx.android.synthetic.main.left_fragment.*

class FragmentActivity : AppCompatActivity() {

    /**
     * 首先我们给左侧Fragment中的按钮注册了一个点击事件，然后调用
    replaceFragment()方法动态添加了RightFragment。当点击左侧Fragment中的按钮时，又会调用replaceFragment()方法，将右侧Fragment替换成AnotherRightFragment。结合replaceFragment()方法中的代码可以看出，动态添加Fragment主要分为5步。
    (1)	创建待添加Fragment的实例。
    (2)	获取FragmentManager，在Activity中可以直接调用getSupportFragmentManager()方法获取。
    (3)	开启一个事务，通过调用beginTransaction()方法开启。
    (4)	向容器内添加或替换Fragment，一般使用replace()方法实现，需要传入容器的id和待添
    加的Fragment实例。
    (5)	提交事务，调用commit()方法来完成。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.two_fragment)
        button.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.addToBackStack(null) //加上这个让出现了动态画面然后返回是回到点击之前， 而不是直接退出
        transaction.commit()
    }

}