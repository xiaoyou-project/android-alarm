package com.xiaoyou.alarm.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * 闹钟相关的数据库初始化
 * @author 小游
 * 这里我们继承了sqlite类,继承的时候实现构造方法，传入context，数据库名字，创建工厂，版本号
 */
class AlarmHelper(context: Context) : SQLiteOpenHelper(context,"alarm.db",null,1){
    /**
     * 当数据库第一次创建的时候执行的方法
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE alarm (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, dec TEXT,time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
    }

    /**
     * 当数据库版本更新后执行的操作
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}