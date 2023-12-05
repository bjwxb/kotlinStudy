package cn.wxb.kt.ui.home.activity.modetest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.wxb.kt.R
import cn.wxb.kt.ui.home.activity.PatientListActivity

class TopLaunchModeActivity : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context){
            Intent().let {
                it.setClass(context, TopLaunchModeActivity::class.java)
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_launch_mode)

        val tv = findViewById<TextView>(R.id.tv)
        tv.setOnClickListener {
            SingleLanuchModeActivity.actionStart(this)
        }
    }

}