package com.xiaoyou.alarm.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.xiaoyou.alarm.R
import com.xiaoyou.alarm.model.Question
import com.xiaoyou.alarm.util.XToastUtils
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : AppCompatActivity() {
    lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        // 隐藏标题栏
        supportActionBar?.hide()
        // 状态栏颜色使用参考 https://github.com/gyf-dev/ImmersionBar
        ImmersionBar.with(this).statusBarColor(R.color.primary).fitsSystemWindows(true).init()
        // 返回主界面
        back.setOnClickListener{finish()}
        // 默认显示第一个题目
        questions = initQuestion()
        showQuestion(0)
    }

    // 显示一个题目
    private fun showQuestion(pos: Int) {
        var index = pos
        if (pos > questions.size - 1) {
           index = 0
        } else if (pos < 0) {
            index = questions.size - 1
        }
        val question = questions[index]
        // 设置题目信息
        question_title.text = question.title
        choose_a.text = question.chooseA
        choose_b.text = question.chooseB
        choose_c.text = question.chooseC
        choose_d.text = question.chooseD
        choose_a.isChecked = false
        choose_b.isChecked = false
        choose_c.isChecked = false
        choose_d.isChecked = false
        // 设置控件的点击事件，用于判题
        choose_a.setOnClickListener{ showInfo(question.right == 1) }
        choose_b.setOnClickListener{ showInfo(question.right == 2) }
        choose_c.setOnClickListener{ showInfo(question.right == 3) }
        choose_d.setOnClickListener{ showInfo(question.right == 4) }
        // 设置上一题和下一题
        next_question.setOnClickListener{ showQuestion(index + 1 ) }
        last_question.setOnClickListener{ showQuestion(index - 1 ) }
    }

    // 显示正确和错误信息
    fun showInfo(boolean: Boolean){
        if (boolean){
            XToastUtils.success("回答正确!")
        } else {
            XToastUtils.error("回答错误!")
        }
    }

    // 题目列表初始化
    private fun initQuestion() : List<Question>{
        return listOf(
            Question("地球是什么形状?","球体","正方体","长方体","圆锥",1),
            Question("猴子喜欢吃什么?","西瓜","草","竹子","香蕉",4),
            Question("下面那部是芳文社的作品?","《JOJO的奇妙冒险》","《名侦探柯南》","《new game》","《海贼王》",3),
            Question("那个网站可以看番剧?","C站","B站","M站","P站",2)
        )
    }
}