package cn.wxb.kt.ui.login

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import cn.wxb.kt.App
import cn.wxb.kt.R
import org.w3c.dom.Text

/**
 * AUTHOR: wuxiaobo
 * DATE: 2024/1/5
 * INTRODUCE: info
 */
object CustomToast {

    fun show(msg:String){
        val context = App.getInstance()
        val toast = Toast(context)
        toast.setGravity(Gravity.CENTER, 0, 0)
        val view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null)
        val tv = view.findViewById<TextView>(R.id.tvToast)
        tv.setText(msg)
        toast.view = view
        toast.duration = Toast.LENGTH_LONG
        toast.show()
    }
}