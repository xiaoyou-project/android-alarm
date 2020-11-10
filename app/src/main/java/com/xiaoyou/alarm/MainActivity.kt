package com.xiaoyou.alarm
import android.app.AlarmManager
import android.content.Intent
import android.graphics.drawable.Animatable
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoyou.alarm.fragment.AlarmFragment
import com.xiaoyou.alarm.fragment.MeFragment
import com.xiaoyou.alarm.fragment.StudyFragment
import com.xiaoyou.alarm.fragment.TaskFragment
import com.xiaoyou.alarm.service.AlarmService
import com.xiaoyou.alarm.sql.AlarmDatabase
import com.xiaoyou.alarm.util.AlarmManageUtil
import nl.joery.animatedbottombar.AnimatedBottomBar
import java.util.*

class MainActivity : AppCompatActivity() ,AnimatedBottomBar.OnTabSelectListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 默认选择第一个页面
        changeFragment(0)
        // 底部按钮监听事件
        bottom_bar.setOnTabSelectListener(this)
        // 启动闹钟服务
//        startService(Intent(this, AlarmService::class.java))
        val alarms = AlarmDatabase.getAllAlarm(this)
        AlarmManageUtil(this,alarms[0]).startAlarm()
    }

    /**
     * 重写底部按钮点击事件
     */
    override fun onTabSelected(
        lastIndex: Int,
        lastTab: AnimatedBottomBar.Tab?,
        newIndex: Int,
        newTab: AnimatedBottomBar.Tab
    ) {
        changeFragment(newIndex)
    }

    private fun changeFragment(newIndex: Int) {
        // fragmentManager初始化
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // 初始化fragment
        val study = StudyFragment()
        val task = TaskFragment()
        val alarm = AlarmFragment()
        val me = MeFragment()
        // 判断不同的fragment
        when (newIndex) {
            0 -> fragmentTransaction.replace(R.id.appFragment,study)
            1 -> fragmentTransaction.replace(R.id.appFragment,task)
            2 -> fragmentTransaction.replace(R.id.appFragment,alarm)
            3 -> fragmentTransaction.replace(R.id.appFragment,me)
        }
        fragmentTransaction.commit()
    }

}