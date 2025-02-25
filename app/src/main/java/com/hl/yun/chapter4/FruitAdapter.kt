package com.hl.yun.chapter4

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fruit_item.view.*

/**
 * @author hl
 * @time
 * @desc ListView适配器
 * FruitAdapter定义了一个主构造函数，用于将Activity的实例、ListView子项布局的id和数
 *  据源传递进来。另外又重写了getView()方法，这个方法在每个子项被滚动到屏幕内的时候会被调用。
 *  在getView()方法中，首先使用LayoutInflater来为这个子项加载我们传入的布局。
 *  LayoutInflater的inflate()方法接收3个参数，前两个参数我们已经知道是什么意思了，第三个参数指定成false，表示只让我们在父布局中声明的layout属性生效，但不会为这个 View添加父布局。因为一旦View有了父布局之后，它就不能再添加到ListView中了。如果你现
 *  在还不能理解这段话的含义，也没关系，只需要知道这是ListView中的标准写法就可以了，当你以后对View理解得更加深刻的时候，再来读这段话就没有问题了。
 *  我们继续往下看，接下来调用View的findViewById()方法分别获取到ImageView和TextView的实例，然后通过getItem()方法得到当前项的Fruit实例，并分别调用它们的 setImageResource()和setText()方法设置显示的图片和文字，最后将布局返回，这样我们自定义的适配器就完成了。
 */
class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
    ArrayAdapter<Fruit>(activity, resourceId, data) {
    //override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    //    //val fruit = getItem(position) // 获取当前项的Fruit实例
    //    //val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
    //    //val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
    //    //val fruitName = view.findViewById<TextView>(R.id.fruitName)
    //    //if (fruit != null) {
    //    //    fruitImage.setImageResource(fruit.imageId)
    //    //    fruitName.text = fruit.name
    //    //}
    //    //return view
    //
    //    // 优化1：缓存复用
    //    val fruit = getItem(position) // 获取当前项的Fruit实例
    //    val view: View
    //    if (convertView == null) {    // 无需重新加载布局，直接使用convertView 进行缓存复用
    //        view = LayoutInflater.from(context).inflate(resourceId, parent, false)
    //    } else {
    //        view = convertView
    //    }
    //    val fruitImage = view.fruitImage
    //    val fruitName = view.fruitName
    //    if (fruit != null) {
    //        fruitImage.setImageResource(fruit.imageId)
    //        fruitName.text = fruit.name
    //    }
    //    return view
    //}

    // 优化2：使用ViewHolder
    /**
     * 我们新增了一个内部类ViewHolder， 用于对ImageView和TextView的控件实例进行缓存，
    Kotlin中使用inner class关键字来定义内部类。当convertView为 null的时候， 创建一个
    ViewHolder对象， 并将控件的实例存放在ViewHolder里， 然后调用
    View的setTag()方法， 将ViewHolder对象存储在View中。当convertView不为null的时候， 则调用View的
    getTag()方法， 把ViewHolder重新取出。这样所有控件的实例都缓存在了ViewHolder里，
    就没有必要每次都通过findViewById()方法来获取控件实例了。
     */

    inner class ViewHolder(
        var fruitImage: ImageView,
        var fruitName: TextView
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val fruit = getItem(position)
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val fruitImage: ImageView = view.fruitImage
            val fruitName: TextView = view.fruitName
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        if (fruit != null) {
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.text = fruit.name
        }
        return view
    }
}