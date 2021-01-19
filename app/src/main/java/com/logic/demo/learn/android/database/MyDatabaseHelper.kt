package com.logic.demo.learn.android.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.logic.demo.learn.android.extension.showToast

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        "Create succeeded".showToast()
    }

    ///更新表,只要传入的version大于之前的version,这个方法就会执行.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Books")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

    // 插入
    fun insert() {
        val db = writableDatabase
        val value1 = ContentValues().apply {
            put("name", "The Da Vinci Code")
            put("author", "Dan Brown")
            put("pages", 454)
            put("price", 16.96)
        }
        db.insert("Book", null, value1)
        val value2 = ContentValues().apply {
            put("name", "The Lost Symbol")
            put("author", "Dan Brown")
            put("pages", 510)
            put("price", 19.95)
        }
        db.insert("Book", null, value2)
    }

    //更新
    fun update() {
        val db = writableDatabase
        val values = ContentValues()
        values.put("price", 10.99)
        db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
    }

    //删除
    fun delete() {
        val db = writableDatabase
        db.delete("Book", "page > ?", arrayOf("500"))
    }

    //查询
    fun select() {
        val db = writableDatabase
        val cursor = db.query("Book", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("author"))
                val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
                Log.d("select", "name:$name, author: $author, pages: $pages, price: $price")
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}