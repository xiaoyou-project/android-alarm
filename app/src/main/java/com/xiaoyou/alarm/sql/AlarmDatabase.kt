package com.xiaoyou.alarm.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.xiaoyou.alarm.model.Alarm
import java.util.*

/**
 * alarm数据库的各种操作
 * @author 小游
 * 本类主要封装了各种数据库的操作
 */
class AlarmDatabase {
    // 这里里面包裹的全部都是静态方法
    companion object {
        var TABLE = "alarm"

        // 插入一条那闹钟的数据
        fun insertData(alarm: Alarm, context: Context){
            // 获取writeable对象
            val db = AlarmHelper(context).writableDatabase
            // 创建一个contentValues用于存放数据
            val value = ContentValues()
            value.put("title", alarm.title)
            value.put("dec",alarm.dec)
            value.put("time",alarm.time.time)
            value.put("status",alarm.status)
            // 数据库插入数据
            db.insert(TABLE,null,value)
            db.close()
        }

        // 获取所有的闹钟数据
        fun getAllAlarm(context: Context):List<Alarm>{
            // 获取read对象
            val db = AlarmHelper(context).readableDatabase
            // 开始查询
            // 这里第的参数分布为 表的名字 查询的列名 查询的条件字句 查询字句对应的条件之 分组方式 having条件 排序方式
            val cursor = db.query(TABLE,null,null,null,null,null,"_id DESC")
            // 这里我们初始化一个可变的list集合 ，如果用listof初始化那么就是不可变的
            val alarms = mutableListOf<Alarm>()
            // 查询的结果不为0
            if (cursor.count != 0){
                // 我们不断移动游标获取值
                while (cursor.moveToNext()){
                    // 这里我们向数据库里面添加一组数据
                    // Date类初始化可以传入unix时间然后转换为Date对象
                    alarms.add(Alarm(cursor.getString(1), Date(cursor.getLong(3)),cursor.getString(2),cursor.getInt(4)))
                }
            }
            cursor.close()
            db.close()
            return alarms
        }
    }
}