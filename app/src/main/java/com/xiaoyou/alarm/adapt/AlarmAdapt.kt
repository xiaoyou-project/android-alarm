package com.xiaoyou.alarm.adapt

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.model.Alarm
import com.xiaoyou.alarm.sql.AlarmDatabase
import com.xiaoyou.alarm.util.Tools

/**
 * @author 小游
 * 这里是闹钟的adapt，里面主要是一个recyclerview的布局
 * 我们这里自己定义了一个adapt类，构造函数只需要传list数据
 * RecyclerView.Adapter里面的泛型需要传递一个holder对象（需要自己继承viewholder类）
 */
class AlarmAdapt(private var alarms:List<Alarm>) : RecyclerView.Adapter<AlarmAdapt.MyHolder>(){

    lateinit var context: Context

    /**
     * 这个holder就是视图的映射，我们这个类里面主要存放视图里面所有的控件信息
     */
    class MyHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        val title: TextView = itemView?.findViewById(R.id.alarm_time)!!
        val dec: TextView = itemView?.findViewById(R.id.alarm_dec)!!
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val status: Switch = itemView?.findViewById(R.id.alarm_switch)!!
    }

    /**
     * 这个主要用于映射item文件
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false))
    }

    /**
     * 这里就是用于修改每个位置的holder文件，holder就是我们自己定义的myHolder类
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // 修改文件信息
        val time = "(" + Tools.time2String(alarms[position].time) + ")"
        holder.title.text = alarms[position].title
        holder.dec.text = alarms[position].dec + time
        holder.status.isChecked = alarms[position].status == 1
        // 这里我们设置开关的点击事件
        holder.status.setOnCheckedChangeListener{ _, isChecked ->
            val alarm = alarms[position]
            if (isChecked) {
                alarm.status = 1
            } else {
                alarm.status = 0
            }
            AlarmDatabase.modifyAlarmById(context,alarm)
        }
    }

    /**
     * 这个用于设置列表的数目
     */
    override fun getItemCount() = alarms.size

    /**
     * 更新list的数据
     */
    fun updateList(data:List<Alarm>){
        this.alarms = data
        this.notifyDataSetChanged()
    }
}