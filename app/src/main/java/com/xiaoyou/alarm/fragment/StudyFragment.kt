package com.xiaoyou.alarm.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.activity.AnswerActivity
import com.xiaoyou.alarm.activity.KnowledgeActivity
import com.xiaoyou.alarm.adapt.StudyAdapt
import com.xiaoyou.alarm.model.Study
import kotlinx.android.synthetic.main.fragment_study.*



class StudyFragment : Fragment(){
    private val study=ArrayList<Study>()
    /**
     * 样式初始化
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_study, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFriends() //初始化列表数据
        // 初始化adapt
        val adapter= StudyAdapt(view.context,R.layout.item_study,study)
        // adapt赋值
        knowledge_list.adapter = adapter
        // 答题按钮点击
        answer.setOnClickListener{
            startActivity(Intent(view.context, AnswerActivity::class.java))
        }
    }

    // 列表初始化
    private fun initFriends(){
        // 集合初始化
        val knowledge = listOf(
            mapOf("title" to "计算机组成与体系结构", "image" to R.mipmap.a,
                "web" to "https://baike.baidu.com/item/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BB%84%E6%88%90%E4%B8%8E%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84",
                "dec" to "主要内容包括：数字逻辑和数字系数、机器层次的数据表示方法、汇编层次的机器组织和结构、存储器的组成和结构、接口和通信、功能组织、多处理器和可供选择的其他结构、性能增强、网络结构和分布式计算机系统等。"),
            mapOf("title" to "操作系统", "image" to R.mipmap.b,
                "web" to "https://baike.baidu.com/item/%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F/192",
                "dec" to "操作系统是一组主管并控制计算机操作、运用和运行硬件、软件资源和提供公共服务来组织用户交互的相互关联的系统软件程序，同时也是计算机系统的内核与基石"),
            mapOf("title" to "数据库系统", "image" to R.mipmap.c,
                "web" to "https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%B3%BB%E7%BB%9F",
                "dec" to "数据库管理系统 是一种针对对象数据库，为管理数据库而设计的大型电脑软件管理系统。"),
            mapOf("title" to "计算机网络", "image" to R.mipmap.d,
                "web" to "https://baike.baidu.com/item/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%BD%91%E7%BB%9C/18763",
                "dec" to "计算机网络（英语：computer network），通常也简称网络，是指容许节点分享资源的数字电信网络[1]:1-3。在电脑网络，电脑设备会透过节点之间的连接（数据链路）互相交换数据。传输介质可分为有线及无线两类——有线的可用到双绞线、光纤电缆等介质。"),
            mapOf("title" to "信息安全", "image" to R.mipmap.e,
                "web" to "https://baike.baidu.com/item/%E4%BF%A1%E6%81%AF%E5%AE%89%E5%85%A8/339810",
                "dec" to "信息安全是指为数据处理系统而采取的技术的和管理的安全保护，保护计算机硬件、软件、数据不因偶然的或恶意的原因而遭到破坏、更改、显露。"),
            mapOf("title" to "软件工程", "image" to R.mipmap.f,
                "web" to "https://baike.baidu.com/item/%E8%BD%AF%E4%BB%B6%E5%B7%A5%E7%A8%8B/25279",
                "dec" to "软件工程是一门研究用工程化方法构建和维护有效的、实用的和高质量的软件的学科。 它涉及程序设计语言、数据库、软件开发工具、系统平台、标准、设计模式等方面。"),
            mapOf("title" to "面向对象程序设计", "image" to R.mipmap.g,
                "web" to "https://baike.baidu.com/item/%E9%9D%A2%E5%90%91%E5%AF%B9%E8%B1%A1%E7%A8%8B%E5%BA%8F%E8%AE%BE%E8%AE%A1/24792",
                "dec" to "面向对象程序设计是种具有对象概念的程序编程典范，同时也是一种程序开发的抽象方针。它可能包含资料、属性、代码与方法。对象则指的是类的实例。")
        )
        // for循环
        for (i in knowledge){
            study.add(Study(i["image"] as Int,i["title"] as String,i["dec"] as String,i["web"] as String))
        }

    }
}