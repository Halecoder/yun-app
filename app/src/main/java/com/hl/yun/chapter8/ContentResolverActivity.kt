package com.hl.yun.chapter8

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_content_resolver.*

/**
 * 不同于SQLiteDatabase，ContentResolver中的增删改查方法都是不接收表名参数的，而是使用一个Uri参数代替，这个参数被称为内容URI。
内容URI给ContentProvider中的数据建立了唯一标识符，它主要由三部分组成：协议申明+authority+path。
协议声明：统一是 content://
authority 区分不同APP，一般采用包名
path 区分同一APP下的不同表名，一般直接表名就可以了内容URI字符串举例：
content://com.example.app.provider/table1
content://com.example.app.provider/table2
val uri = Uri.parse("content://com.example.app.provider/table1")
只需要调用Uri.parse()方法，就可以将内容URI字符串解析成Uri对象，Uri对象才能当参数传递
 */
class ContentResolverActivity : AppCompatActivity() {
    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)
        //给ListView控件做Adapter适配器
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
        contactsView.adapter = adapter
        //检查是否授权
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            //没有授权就申请个（ 运行时申请）
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS), 1
            )
        } else {
            //有了就直接开始读取通讯录联系人
            readContacts()
        }
    }

    //对运行时申请权限结 果的处理
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    readContacts()
                } else {
                    //弹出申请权限被拒绝的提示
                    Toast.makeText(
                        this, "You denied the permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    //读取联系人的方法
    @SuppressLint("Range")
    private fun readContacts() {
        // 查询联系人数据
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, //这是类已经帮我们做好了封装，提供了一个 CONTENT_URI 常量， 而这个常量就是使用 Uri.parse() 方法解析出来的结果。
            null, null, null, null
        )?.apply {
            while (moveToNext()) {
                // 获取联系人姓名
                val displayName = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                )
                // 获取联系人手机号
                val number = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
                contactsList.add("$displayName\n$number")
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }
}