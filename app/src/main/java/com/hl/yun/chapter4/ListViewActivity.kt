package com.hl.yun.chapter4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hl.yun.R
import com.hl.yun.databinding.ListViewBinding

class ListViewActivity : AppCompatActivity() {
    //private val data = listOf(
    //    "Apple",
    //    "Banana",
    //    "Orange",
    //    "Watermelon",
    //    "Pear",
    //    "Grape",
    //    "Pineapple",
    //    "Strawberry",
    //    "Cherry",
    //    "Mango",
    //    "Apple",
    //    "Banana",
    //    "Orange",
    //    "Watermelon",
    //    "Pear",
    //    "Grape",
    //    "Pineapple",
    //    "Strawberry",
    //    "Cherry",
    //    "Mango"
    //)
    //
    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    setContentView(R.layout.list_view)
    //    /**
    //     * ListView 的适配器
    //     * 其中  ListView 是不能直接接收对象的，需要个适配器。其中最好用的是
    //    ArrayAdapter .他需要传三个参数：当前Avtivity的上下文，ListView子类布局是什么样子，数据源。其中android.R.layout.simple_list_item_1 作为ListView子项布局的id，这是一个
    //    Android内置的布局文件，里面只有一个TextView，可用于简单地显示一段文本。
    //    而且这里通过泛型来指定适配的数据类型，因为我们是字符串所以我们直接用String
    //     */
    //    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
    //    listView.adapter = adapter
    //}

    private lateinit var binding: ListViewBinding
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)
        binding = ListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits()
        binding.listView.adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
    }

    private fun initFruits() {
        repeat(50) {
            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            fruitList.add(Fruit("cherry", R.drawable.cherry_pic))
        }
    }
}