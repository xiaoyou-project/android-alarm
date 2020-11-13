package com.xiaoyou.alarm.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.activity.DataSaveActivity
import kotlinx.android.synthetic.main.fragment_other.*

/**
 * @description
 * @author 小游
 * @data 2020/11/13
 */
class OtherFragment: Fragment() , View.OnClickListener{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            var intent = Intent(this.context,DataSaveActivity::class.java)
            when(v.id){
                R.id.dataSave -> intent = Intent(this.context,DataSaveActivity::class.java)
            }
            startActivity(intent)

        }
    }
}