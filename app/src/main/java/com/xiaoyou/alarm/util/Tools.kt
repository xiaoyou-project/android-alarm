package com.xiaoyou.alarm.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @description 封装一些常用的工具类
 * @author 小游
 * @data 2020/11/04
 */
class Tools {
    companion object{
        fun time2String(time:Date):String{
            return SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss").format(time)
        }
    }
}