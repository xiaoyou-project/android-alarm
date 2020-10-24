package com.xiaoyou.alarm.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.xiaoyou.alarm.R
import kotlinx.android.synthetic.main.activity_knowledge.*

class KnowledgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge)
        // 获取数据
        val bundle = intent.extras
        val url = bundle?.getString("web")
        // 打开网址
        AgentWeb.with(this)
            .setAgentWebParent(web, ViewGroup.LayoutParams(-1,-1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(url)
    }
}