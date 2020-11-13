package com.xiaoyou.alarm

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.xiaoyou.alarm.sql.AlarmDatabase


import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class SqlTest {

    // 测试数据库连接
    @Test
    fun getAllResult(){
        // 获取context对象
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // 测试函数是否有效
        val alarms = AlarmDatabase.getAllAlarm(context)
        for (alarm in alarms){
            println(alarm.title)
        }
    }
}