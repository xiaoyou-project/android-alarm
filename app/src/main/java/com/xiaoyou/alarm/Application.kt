package com.xiaoyou.alarm

import android.app.Application
import com.xuexiang.xui.XUI

open class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
    }
}