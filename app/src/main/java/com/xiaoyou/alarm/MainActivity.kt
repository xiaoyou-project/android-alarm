package com.xiaoyou.alarm
import android.graphics.drawable.Animatable
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoyou.alarm.fragment.StudyFragment
import nl.joery.animatedbottombar.AnimatedBottomBar
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_bar.setOnTabSelectListener(object :AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val study = StudyFragment()
                // 判断不同的fragment
                when (newIndex) {
                    0 -> fragmentTransaction.add(R.id.appFragment, study)
                }
                fragmentTransaction.commit()
            }
        })
    }
}