package com.xiaoyou.alarm.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.xiaoyou.alarm.R
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        // 隐藏标题栏
        supportActionBar?.hide()
        // 状态栏颜色使用参考 https://github.com/gyf-dev/ImmersionBar
        ImmersionBar.with(this).statusBarColor(R.color.primary).fitsSystemWindows(true).init()
        // 返回主界面
        back.setOnClickListener{finish()}
    }
}