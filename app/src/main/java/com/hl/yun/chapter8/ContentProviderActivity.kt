package com.hl.yun.chapter8

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.hl.yun.R
import kotlinx.android.synthetic.main.activity_content_provider.*

class ContentProviderActivity : AppCompatActivity() {
    var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        addData.setOnClickListener {
            // 添加数据
            val uri =
                Uri.parse("content://com.hl.yun.chapter8.MyProvider/book")
            val values = contentValuesOf(
                "name" to "新神传 杨戬",
                "author" to "猫眼娱乐", "pages" to 140, "price" to 22.8
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }
        deleteData.setOnClickListener {
            // 删除数据
            bookId?.let {
                val uri =
                    Uri.parse("content://com.hl.yun.chapter8.MyProvider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }


        queryData.setOnClickListener {    // 查

            val uri = Uri.parse("content://com.hl.yun.chapter8.MyProvider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    //下面的代码和《第一行代码  第三版》中有出入，
                    val name = getString(getColumnIndexOrThrow("name"))
                    val author = getString(getColumnIndexOrThrow("author"))
                    val pages = getInt(getColumnIndexOrThrow("pages"))
                    val price = getDouble(getColumnIndexOrThrow("price"))
                    Log.d("ContentProviderActivity", "book name is $name")
                    Log.d("ContentProviderActivity", "book author is $author")
                    Log.d("ContentProviderActivity", "book pages is $pages")
                    Log.d("ContentProviderActivity", "book price is $price")
                }
                close()
            }
        }
        updateData.setOnClickListener {
            bookId?.let {
                val uri =
                    Uri.parse("content://com.hl.yun.chapter8.MyProvider/book/$it")
                val values = contentValuesOf("name" to "明日战纪", "pages" to 999, "price" to 0.01)
                contentResolver.update(uri, values, null, null)
            }
        }
    }
}