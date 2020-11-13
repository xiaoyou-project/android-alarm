package com.xiaoyou.alarm.activity

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.xiaoyou.alarm.R
import kotlinx.android.synthetic.main.activity_data_save.*
import java.io.File

class DataSaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_save)

        // 不同存储逻辑
        save_data_in_storage.setOnClickListener{
            val fileName = "test.txt"
            val content = "hello,word!"
            // 先设置内部存储
            // 设置其他应用无法访问 这里会存储在/data/data/packname/files 文件夹里面
            this.openFileOutput(fileName, MODE_PRIVATE).use {
                it.write(content.toByteArray())
                it.close()
            }

            // 把文件写入缓存，这里缓存用于缓存某些目录
            // 参考https://developer.android.com/training/data-storage/files/internal?hl=zh-cn#kotlin
        }

        // 读取缓存的文件
        read_data_in_storage.setOnClickListener{
            this.openFileInput("test.txt").use {
                // 初始化一个字节数组
                val data = ByteArray(it.available())
                // 读取数据到数组里面去
                it.read(data)
                it.close()
                // 显示读取的数据,我们把字节数据转换为文本数据
                Toast.makeText(this, String(data),Toast.LENGTH_LONG).show()
            }
        }
    }


}