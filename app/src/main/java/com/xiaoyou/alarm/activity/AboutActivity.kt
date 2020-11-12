package com.xiaoyou.alarm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoyou.alarm.R
import com.xuexiang.xui.widget.grouplist.XUIGroupListView
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        version.text = String.format("版本号 %s", "11")
        XUIGroupListView.newSection(this)
            .addItemView(about_list.createItemView("访问官网")) {}
            .addItemView(about_list.createItemView("说明文档")) {}
            .addItemView(about_list.createItemView("github")) {}
            .addItemView(about_list.createItemView("赞助")) {}
            .addTo(about_list)
    }
}