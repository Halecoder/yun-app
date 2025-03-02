package com.hl.yun.chapter8

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri

/**
 * 重写 ContentProvider,自定义 ContentProvider
 */
class MyProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val authority = "com.hl.yun.chapter8.MyProvider"
    private lateinit var dbHelper: ProviderDatabaseHelper //这里只是拿到了SQLite类的实例

    //替UriMatcher做的准备
    /*
*只有by才是Kotlin中的关键字， lazy在这里只是一个高阶函数而已。在lazy函数中会创建
并返回一个Delegate对象， 当我们调用p属性的时候， 其实调用的是
Delegate对象的
getValue()方法， 然后getValue()方法中又会调用lazy函数传入的
Lambda表达式， 这样
表达式中的代码就可以得到执行了， 并且调用p属性后得到的值就是
Lambda表达式中最后一行代码的返回值。
*/
    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(authority, "book", bookDir)
            addURI(authority, "book/#", bookItem)
            addURI(authority, "category", categoryDir)
            addURI(authority, "category/#", categoryItem)
        }
        matcher
    }

    /**
     *(1) onCreate()。初始化ContentProvider的时候调用。通常会在这里完成对数据库的创建和升级等操作， 返回true表示ContentProvider初始化成功， 返回false   则表示失败。
     */
    //Getter语法糖， ？ .操作符， let函数， ？ :操作符， 单行代码函数语法糖
    override fun onCreate() = context?.let {
        dbHelper = ProviderDatabaseHelper(it, "BookStore.db", 2)
        true
    } ?: false

    /**
     *(2) query()。从ContentProvider中查询数据。uri参数用于确定查询哪张表， projection参数用于确定查询哪些列， selection和
    selectionArgs参数用于约束查询哪些行， sortOrder参数用于对结果进行排序， 查询的结果存放在Cursor对象中返回。
     */
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper.let {
        //查询数据
        val db = it.readableDatabase //我们这里是查询所以用readable就可以了
        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query(
                "Book",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            bookItem -> {
                /**
                 *将内容URI权限之后的部分以“/”符号进行分割， 并把分割后
                 *的结果放入一个字符串列表中， 那这个列表的第0个位置存放的就是路径， 第1个位置存放的就是id
                 */
                val bookId = uri.pathSegments[1]
                db.query(

                    "Book",
                    projection,
                    "id = ?",
                    arrayOf(bookId),
                    null,
                    null,
                    sortOrder
                )
            }
            categoryDir -> db.query(
                "Category",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query(
                    "Category",
                    projection,
                    "id = ?",
                    arrayOf(categoryId),
                    null,
                    null,
                    sortOrder
                )
            }
            else -> null
        }
        cursor
    }

    /**
    (3) insert()。向ContentProvider中添加一条数据。uri参数用于确定要添加到的表， 待添加的数据保存在values参数中。添加完成后， 返回一个用于表示这条新记录	的URI。
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? = dbHelper.let {
        // 添加数据
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values) //新插入行的行Id， 异常就是-1
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        return uriReturn //返回被修改数据的URI信息
    }

    /**
     *(4) update()。更新ContentProvider中已有的数据。uri参数用于确定更新哪一张表中的数据， 新数据保存在values参数中， selection和 selectionArgs参数用于	约束更新哪些行， 受影响的行数将作为返回值返回。
     */
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?
    ) = dbHelper.let {
        // 更新数据
        val db = it.writableDatabase
        val updatedRows = when (uriMatcher.match(uri)) {
            bookDir -> db.update("Book", values, selection, selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book", values, "id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update(
                "Category", values, selection,
                selectionArgs
            )
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        updatedRows
    }

    /**
     *(5) delete()。从ContentProvider中删除数据。uri参数用于确定删除哪一张表中的数据， selection和selectionArgs参数用于约束删除哪些行， 被删除的行数将   作为返回值返回。
     */
    override fun delete(
        uri: Uri, selection: String?, selectionArgs: Array<String>
        ?
    ) =
        dbHelper.let {
            //删除数据
            val db = it.writableDatabase //拿到操作数据库的对象
            val deleteRow = when (uriMatcher.match(uri)) {
                bookDir -> db.delete("Book", selection, selectionArgs)
                bookItem
                -> {
                    val bookId = uri.pathSegments[1] //【1】是id，【0】是路径
                    db.delete("Book", "id = ?", arrayOf(bookId))
                }
                categoryDir -> db.delete(
                    "Category", selection,
                    selectionArgs
                )
                categoryItem -> {
                    val categoryId = uri.pathSegments[1]
                    db.delete("Category", "id = ?", arrayOf(categoryId))
                }
                else -> 0
            }
            deleteRow
        }

    /**
     *(6) getType()。根据传入的内容URI返回相应的MIME类型。
     *  table1Dir -> "vnd.android.cursor.dir/vnd.com.example.app.provider.table1"
    table1Item -> "vnd.android.cursor.item/vnd.com.example.app.provider.table1"
    table2Dir -> "vnd.android.cursor.dir/vnd.com.example.app.provider.table2"
    table2Item -> "vnd.android.cursor.item/vnd.com.example.app.provider.table2"
    else -> null
     */

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        bookDir ->
            "vnd.android.cursor.dir/vnd.com.hl.yun.chapter8.MyProvider.book"
        bookItem ->
            "vnd.android.cursor.item/vnd.com.hl.yun.chapter8.MyProvider.book"
        categoryDir ->
            "vnd.android.cursor.dir/vnd.com.hl.yun.chapter8.MyProvider.category"
        categoryItem ->
            "vnd.android.cursor.item/vnd.com.hl.yun.chapter8.MyProvider.category"
        else -> null
    }
    //    为什么  ContentResolver能保证隐私数据不会泄漏出去呢？
    //因为所有的增删改查操作都一定要匹配到相应的内容URI格式才能进行，而我们当然不可能向
    //UriMatcher中添加隐私数据的URI，所以这部分数据根本无法被外部程序访问，安全问题也就不存在了
}
