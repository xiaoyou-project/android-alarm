package com.xiaoyou.alarm

import com.xiaoyou.alarm.util.Tools
import org.junit.Test
import java.util.*

/**
 * @description 测试数据库连接
 * @author 小游
 * @data 2020/11/13
 */
class ToolTest {
    @Test
    fun testTime2String(){
        // 测试时间转换
       println(Tools.time2String(Date()))
    }
}