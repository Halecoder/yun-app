package com.hl.yun.chapter4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hl.yun.R

class FruitAdapterByRecycle(val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapterByRecycle.ViewHolder>() {
    // //ViewHolder的主构造函数中要传入一个View参数， 这个参数通常就是RecyclerView子项的最外层布局
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        var fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    /**
     * 将fruit_item布局加载进来， 然后创建一个ViewHolder实例， 并把加载出来的布局传入构造
    函数当中， 最后将ViewHolder的实例返回。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    //用于对 RecyclerView子项的数据进行赋值， 会在每个子项被滚动到屏幕内的时候执行
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(
                holder.itemView.context,
                "you click view ${fruit.name}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        holder.fruitImage.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(
                holder.fruitImage.context,
                "you click image ${fruit.name}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun getItemCount() = fruitList.size
}