package com.xiaoyou.alarm
import android.app.AlarmManager
import android.content.Intent
import android.graphics.drawable.Animatable
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoyou.alarm.fragment.*
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
        if (alarms.isNotEmpty()){
            AlarmManageUtil(this,alarms[0]).startAlarm()
        }
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

    // 点击不同的fragment跳转不同的页面
    private fun changeFragment(newIndex: Int) {
        // fragmentManager初始化
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // 初始化fragment
        val study = StudyFragment()
        val task = TaskFragment()
        val alarm = AlarmFragment()
        val me = MeFragment()
        val other = OtherFragment()
        // 判断不同的fragment
        when (newIndex) {
            0 -> fragmentTransaction.replace(R.id.appFragment,study)
            1 -> fragmentTransaction.replace(R.id.appFragment,task)
            2 -> fragmentTransaction.replace(R.id.appFragment,alarm)
            3 -> fragmentTransaction.replace(R.id.appFragment,me)
            4 -> fragmentTransaction.replace(R.id.appFragment,other)
        }
        fragmentTransaction.commit()
    }

    // 重写启动事件
    override fun onStart() {
        super.onStart()
        //        不同日志级别的打印
        Log.v("xiaoyou","项目启动")
        Log.d("xiaoyou","项目启动")
        Log.i("xiaoyou","项目启动")
        Log.w("xiaoyou","项目启动")
        Log.e("xiaoyou","项目启动")
    }

    override fun onResume() {
        super.onResume()
        Log.i("xiaoyou","获取焦点")
    }

    override fun onPause() {
        super.onPause()
        Log.i("xiaoyou","锁屏或被其他activity覆盖的时候调用")
    }

    override fun onStop() {
        super.onStop()
        Log.i("xiaoyou","activity对用户不可见的时候调用")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("xiaoyou","activity销毁的时候调用")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("xiaoyou","activity从停止状态再次启动的时候调用")
    }

}