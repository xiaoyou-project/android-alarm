package com.xiaoyou.alarm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoyou.alarm.R
import kotlinx.android.synthetic.main.activity_forget.*

class ForgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)
        btn_reset.setOnClickListener{ finish() }
    }
}