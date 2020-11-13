package com.xiaoyou.alarm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoyou.alarm.R
import kotlinx.android.synthetic.main.activity_start_task.*

class StartTaskActivity : AppCompatActivity() {
    private var process: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_task)
        // 监听滑动控件的任务进度
        xsb.setOnSeekBarListener{_, newValue ->
            process = newValue
            // 注意不能放到销毁方法里面，这个setResult会在控件销毁的时候自动返回
            setResult(1,Intent().putExtra("process",process))
            Log.e("xiaoyou", "进度$process")
        }
    }

}