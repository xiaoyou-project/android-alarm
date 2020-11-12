package com.xiaoyou.alarm.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.activity.AboutActivity
import com.xiaoyou.alarm.activity.ForgetActivity
import com.xiaoyou.alarm.util.XToastUtils
import com.xuexiang.xui.utils.CountDownButtonHelper
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment  : Fragment(),View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    /**
     * 页面处理事件
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_get_verify_code.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        tv_other_login.setOnClickListener(this)
        tv_forget_password.setOnClickListener(this)
        tv_user_protocol.setOnClickListener(this)
        tv_privacy_protocol.setOnClickListener(this)
    }

    /**
     * 按钮点击事件监听
     */
    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_get_verify_code -> if (et_phone_number.validate()) getVerifyCode(
                et_phone_number.editValue
            )
            R.id.btn_login -> if (et_phone_number.validate() && et_verify_code.validate()) XToastUtils.success("登录成功")
            R.id.tv_other_login -> XToastUtils.info("其他登录方式")
            R.id.tv_forget_password -> startActivity(Intent(v.context,ForgetActivity::class.java))
            R.id.tv_user_protocol -> startActivity(Intent(v.context,AboutActivity::class.java))
            R.id.tv_privacy_protocol -> XToastUtils.info("隐私政策")
        }
    }

    /**
     * 获取验证码
     */
    private fun getVerifyCode(phoneNumber: String) {
        // TODO: 2019-11-18 这里只是界面演示而已
        XToastUtils.warning("只是演示，验证码请随便输")
        // 显示倒计时
        CountDownButtonHelper(btn_get_verify_code, 60).start()
    }
}