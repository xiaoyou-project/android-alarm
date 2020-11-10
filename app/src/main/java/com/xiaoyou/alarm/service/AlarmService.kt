package com.xiaoyou.alarm.service

import android.app.AlertDialog
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.sql.AlarmDatabase
import com.xiaoyou.alarm.util.Permission
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog
import java.lang.Exception

class AlarmService : Service() {

    // 我们使用alarmManage来设置闹钟任务
    private  var id = 0

    override fun onBind(intent: Intent): IBinder {
//        this.intent = intent
        TODO("Return the communication channel to the service.")
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate() {
//        super.onCreate()
//        // 先判断用户是否有弹窗权限
//        if (!Settings.canDrawOverlays(this)){
//            try {
//                Permission(this).requestAlertWindowsPermission()
//            }catch (e :Exception){
//                Log.e("xiaoyou", "出错了")
//            }
//        } else {
//            // 获取闹钟数据
//            val alarm = AlarmDatabase.getAlarmById(this,id)
//            // 显示弹窗
//            val builder=AlertDialog.Builder(applicationContext)
//            builder.setTitle(alarm.title)
//            builder.setMessage("闹钟描述:${alarm.dec}")
//            builder.setNegativeButton("确定",null)
//            val dialog=builder.create()
//            dialog.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
//            dialog.show()
//        }
//    }

    // 因为我们需要获取传过来的数据，所以我们直接重写onStartCommand方法即可
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            // 获取闹钟id
            id = intent.getIntExtra("id", 0)
            // 先判断用户是否有弹窗权限
            if (!Settings.canDrawOverlays(this)){
                try {
                    Permission(this).requestAlertWindowsPermission()
                }catch (e :Exception){
                    Log.e("xiaoyou", "出错了")
                }
            } else {
                // 获取闹钟数据
                val alarm = AlarmDatabase.getAlarmById(this,id)
                // 显示弹窗
                val builder=AlertDialog.Builder(applicationContext)
                builder.setTitle(alarm.title)
                builder.setMessage("闹钟描述:${alarm.dec}")
                builder.setNegativeButton("确定",null)
                val dialog=builder.create()
                dialog.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                dialog.show()
            }
        }
        Log.e("xiaoyou", "你设定的闹钟响了哦！！！！")
        return super.onStartCommand(intent, flags, startId)
    }
}