package com.xiaoyou.alarm.adapt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.just.agentweb.AgentWeb
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.activity.KnowledgeActivity
import com.xiaoyou.alarm.model.Study

/**
 * 学习列表的adapt
 * @author 小游
 *  (activity: Context, private val id:Int, data:List<Study>)，这个就是adapt的构造函数，里面是初始化adapt锁需要的参数
 *  : ArrayAdapter<Study>(activity,id,data) : 这个用于类实现接口或者继承类，如果是接口那么就不需要加括号，继承的话就需要加上括号
 *  因为arrayAdapt是一个泛型类 <>里面表示泛型的类型
 *  然后后面那个(activity,id,data)就是继承类的初始化，因为这个继承类有构造函数，继承的时候要初始化这个构造函数
 *  以往的java中如果继承的父类中有构造函数，那么就必须使用super(activity,id,data)来进行初始化，我们这里省去了super，直接初始化
 */
class StudyAdapt(activity: Context, private val id:Int, data:List<Study>) : ArrayAdapter<Study>(activity,id,data) {
    // 我们这里创建一个viewHolder来缓存控件数据，这里使用viewHolder的好处就是把控件的数据缓存起来，以供后面的函数调用
    inner class ViewHolder(val image:ImageView,val title:TextView,val dec:TextView)
    // 这里我们重写了getView的方法，这里我们就负责样式的初始化
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val holder : ViewHolder
        // 这里的getItem是ArrayAdapter里面的一个方法，我们通过这个可以获取之前泛型里面的数据
        val study=getItem(position)//获取当前项得Fruit实例
        // 因为这个getView在进行加载的时候，每次布局都会重新加载一遍
        // convertView这个参数用于将之前的加载好的布局进行缓存。以便之后进行重用，所以我们可以进行判断，避免重新加载
        if (convertView == null){
            // 如果布局为空，那么我们就指定布局id
            view = LayoutInflater.from(context).inflate(id,parent,false)
            // 获取控件信息，然后放到holder里面
            val image: ImageView =view.findViewById(R.id.item_image)
            val title: TextView =view.findViewById(R.id.item_title)
            val dec: TextView =view.findViewById(R.id.item_dec)
            // 把holder绑定到tag上进行存储，方便后面直接调用
            holder = ViewHolder(image,title, dec)
            view.tag = holder
            view.setOnClickListener{
                // activity页面跳转
                val intent = Intent(view.context, KnowledgeActivity::class.java)
                // activity携带信息,我们把数据封装到bundle中
                val bundle = Bundle()
                if (study != null) {
                    bundle.putString("web", study.web)
                    bundle.putString("title", study.title)
                }
                intent.putExtras(bundle)
                // 启动activity
                view.context.startActivity(intent)
            }
        }else {
            // 如果有缓存了，那么我们就直接调用就可
            view = convertView
            holder = view.tag as ViewHolder
        }
        // 判断是否有数据，如果有的话我们就进行设置布局操作
        if (study!=null){
            // 这里我们直接用holder里面的数据来设置控件
            holder.image.setImageResource(study.image)
            holder.title.text = study.title
            holder.dec.text = study.dec
        }
        return  view
    }
}