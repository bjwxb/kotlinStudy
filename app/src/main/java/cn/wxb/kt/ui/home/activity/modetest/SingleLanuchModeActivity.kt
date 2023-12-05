package cn.wxb.kt.ui.home.activity.modetest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.wxb.kt.R

class SingleLanuchModeActivity : AppCompatActivity() {


    companion object{
        fun actionStart(context: Context){
            Intent().let {
                it.setClass(context, SingleLanuchModeActivity::class.java)
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_lanuch_mode)

        val tv = findViewById<TextView>(R.id.tv)
        tv.setOnClickListener {
            SingleInstanceLaunchActivity.actionStart(this)
        }
    }
}