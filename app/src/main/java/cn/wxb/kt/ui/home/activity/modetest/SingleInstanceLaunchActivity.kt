package cn.wxb.kt.ui.home.activity.modetest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.wxb.kt.R

class SingleInstanceLaunchActivity : AppCompatActivity() {

   companion object{
       fun actionStart(context: Context){
           Intent().let {
               it.setClass(context, SingleInstanceLaunchActivity::class.java)
               context.startActivity(it)
           }
       }
   }


    var i = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance_launch)

        val tv = findViewById<TextView>(R.id.tv)
        tv.setOnClickListener {
            i++
            tv.setText("singel instance i = $i")
            StandardLaunchModeActivity.actionStart(this)
        }
    }
}