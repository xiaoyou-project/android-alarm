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

        // 开始任务的点击事件
        start_task.setOnClickListener{ startActivity(Intent(this,StartTaskActivity::class.java))}
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