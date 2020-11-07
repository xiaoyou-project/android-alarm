package com.xiaoyou.alarm.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AlarmService : Service() {
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("xiaoyou","服务已启动")
    }

}