package com.xiaoyou.alarm.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xiaoyou.alarm.R
import com.xuexiang.xui.widget.progress.CircleProgressView.CircleProgressUpdateListener
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity(), CircleProgressUpdateListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        // 设置环形进度条的数据
        progressView_circle_main.setGraduatedEnabled(true)
        // 设置监听事件
        progressView_circle_main.setProgressViewUpdateListener(this)
        // 开始显示进度条
        progressView_circle_main.startProgressAnimation()

        // 获取传递过来的数据
        val title = intent.getStringExtra("title")
        task_title.text = title

        // 开始任务的点击事件(这里可以监听数据的回传 )
        start_task.setOnClickListener{ startActivityForResult(Intent(this,StartTaskActivity::class.java),1)}
    }

    // 当监听到数据回传的时候回触发这个方法
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 获取上一步设置的进度条
        val process = data?.getIntExtra("process",0)
        Log.e("xiaoyou","数据回传$process,$requestCode,$resultCode")
        if (process != null && requestCode == 1 && resultCode == 1) {
            Log.e("xiaoyou", "获取到进度数据$process")
            // 获取返回的数据并设置动画效果
            progressView_circle_main.setEndProgress(process.toFloat())
            progressView_circle_main.startProgressAnimation()
        }
    }



    override fun onCircleProgressStart(view: View?) {
    }

    override fun onCircleProgressUpdate(view: View?, progress: Float) {
        // 实时把进度条的内容显示出来
        progress_text_main.text = progress.toInt().toString()
    }

    override fun onCircleProgressFinished(view: View?) {
    }
}