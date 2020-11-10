package com.xiaoyou.alarm.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.adapt.AlarmAdapt
import com.xiaoyou.alarm.model.Alarm
import com.xiaoyou.alarm.service.AlarmService
import com.xiaoyou.alarm.sql.AlarmDatabase
import com.xiaoyou.alarm.util.Tools
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText
import com.xuexiang.xui.widget.picker.widget.TimePickerView
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.menu_float_button.*
import java.text.SimpleDateFormat
import java.util.*

class AlarmFragment  : Fragment() {
    // 我们这里使用latinit属性就是说明我们再别的地方初始化
    private lateinit var timePickerView:TimePickerView
    // 闹钟选择的试卷
    private lateinit var choose:Date
    // 闹钟的adapt
    private lateinit var alarmAdapt: AlarmAdapt

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化一个viewManager，使用的是线性布局，就是直接一列列的显示
        val viewManager = LinearLayoutManager(this.context)
        // 这里我们利用异常处理，如果有异常我们就不赋值，直接返回空list
        val alarms = getAllAlarm(this.context!!)
        // 初始化adapter
        alarmAdapt = AlarmAdapt(alarms)
        // 这里我们直接给recycleview赋值 这个{}类似于lambda表达式
        alarm_list.apply {
            // 这个是设置内容中的内容不要更改RecyclerView的布局大小
            setHasFixedSize(true)
            // 这里设置layoutmanage
            layoutManager = viewManager
            // 这里设置adapt信息
            adapter = alarmAdapt
        }
        // 添加闹钟的点击事件
        fab_add.setOnClickListener{
            // 显示自定义弹框
             val dialog = MaterialDialog.Builder(this.context!!)
                 .customView(R.layout.custom_add_alarm, true)
                 .cancelable(false)
                 .title("添加闹钟")
                 .positiveText("添加")
                 .negativeText("取消")
                 .onPositive{ dialog,_ ->
                     val title: MaterialEditText = dialog.view.findViewById(R.id.alarm_title)
                     val dec: MaterialEditText = dialog.view.findViewById(R.id.alarm_dec)
                     val alarm = Alarm(title.editValue,choose,dec.editValue,1)
                     AlarmDatabase.insertData(alarm,this.context!!)
                     // 插入数据后更新数据
                     alarmAdapt.updateList(getAllAlarm(this.context!!))
                     Log.e("xiaoyou",alarm.title)
                }.show()
            // 设置设置闹钟的点击按钮
            val v = dialog.view
            val set: Button = v.findViewById(R.id.set_time)
            val time: TextView = v.findViewById(R.id.time_show)
            // 时间选择按钮点击事件
            set.setOnClickListener{
                val calendar = Calendar.getInstance()
                // 这里我们判断变量是否初始化
                if (this::choose.isInitialized){
                    calendar.time = choose
                } else {
                    calendar.time = Date()
                }
                timePickerView = TimePickerBuilder(this.context, null)
                    .setTimeSelectChangeListener { date ->
                        // 设置当前的时间
                        choose = date
                        time.text = Tools.time2String(date)
                    }
                    .setType(TimePickerType.ALL)
                    .isDialog(true)
                    .setTitleText("时间选择")
                    .setDate(calendar)
                    .build()
                timePickerView.show()
            }

        }
    }

    // 获取所有的闹钟信息
    private fun getAllAlarm(context: Context):List<Alarm>{
        return try {
            // 这里我们获取数据库里面所有的闹钟信息
            AlarmDatabase.getAllAlarm(context)
        } catch (e: Exception){
            listOf<Alarm>()
        }
    }
}