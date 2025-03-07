package com.hl.yun.chapter4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hl.yun.R
import kotlinx.android.synthetic.main.recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)
        initFruits() // 初始化水果数据
        //val layoutManager = LinearLayoutManager(this)
        ////实现横向列表
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        //recyclerView.layoutManager = layoutManager

        // 实现瀑布布局
        val layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL) //这里换成了瀑布布局
        recyclerView.layoutManager = layoutManager

        val adapter = FruitAdapterByRecycle(fruitList)
        recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(10) {
            fruitList.add(Fruit(getRandomLengthString("Apple"), R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("Banana"), R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("Cherry"), R.drawable.cherry_pic))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}