package com.xiaoyou.alarm.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.activity.TaskDetailActivity
import com.xuexiang.xui.widget.grouplist.XUIGroupListView
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.fragment_task.*

class TaskFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(view.context, TaskDetailActivity::class.java)
        // 显示列表数据
        XUIGroupListView.newSection(this.context)
            .addItemView(task_list.createItemView("吃饭")) {startActivity(intent.putExtra("title","吃饭").putExtra("process",20))}
            .addItemView(task_list.createItemView("看番")) {startActivity(intent.putExtra("title","看番").putExtra("process",20))}
            .addItemView(task_list.createItemView("玩游戏")) {startActivity(intent.putExtra("title","玩游戏").putExtra("process",20))}
            .addItemView(task_list.createItemView("睡觉")) {startActivity(intent.putExtra("title","睡觉").putExtra("process",20))}
            .addTo(task_list)
    }
}