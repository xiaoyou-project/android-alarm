package com.xiaoyou.alarm.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.xiaoyou.alarm.model.Alarm
import com.xiaoyou.alarm.service.AlarmService
import java.util.*


/**
 * @description 闹钟时间处理的类
 * @author 小游
 * @data 2020/11/09
 */
class AlarmManageUtil(var context: Context,var alarm: Alarm) {
// 构造函数
    init {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmService::class.java)
        intent.putExtra("id",alarm.id)
        pendingIntent = PendingIntent.getService(context, 0, intent, 0)
    }

//    静态变量区域
    companion object{
        // 闹钟执行的时间间隔
        const val TIME_INTERVAL = 2 * 1000 // 10秒执行一次重复任务
        private lateinit var alarmManager: AlarmManager
        private lateinit var pendingIntent: PendingIntent
    }

    private lateinit var calendar: Calendar


    /**
     * 启动一个闹钟
     */
    fun startAlarm(){
        // 判断这个闹钟是否过期
        if (alarm.time > Date()){
            //  设置时间
            calendar = Calendar.getInstance()
            calendar.time = alarm.time
            // 设置定时任务
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                pendingIntent
            )
        } else {
            Log.e("xiaoyou","你的闹钟过期了")
        }

    }

    // 设置一个重复的任务(这里好像设置不了。。。，算了先不管这个)
    fun startRepeatTask(){
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),
//            TIME_INTERVAL.toLong(),
//            pendingIntent)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+ TIME_INTERVAL,
            pendingIntent
        )
    }
}