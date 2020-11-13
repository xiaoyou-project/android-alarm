package com.xiaoyou.alarm

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import android.util.Log
import androidx.constraintlayout.utils.widget.MockView
import androidx.test.core.app.ApplicationProvider
import com.xiaoyou.alarm.sql.AlarmDatabase
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.coroutineContext

/**
 * @description 测试数据库连接
 * @author 小游
 * @data 2020/11/13
 */
class SqlTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    @Test
    fun getAllResult(){
        val alarms = AlarmDatabase.getAllAlarm(context)
        for (alarm in alarms){
            println(alarm.title)
        }
    }

}