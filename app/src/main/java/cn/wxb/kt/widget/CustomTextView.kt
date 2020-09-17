package cn.wxb.kt.widget

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.LogUtils


/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/22 9:51
 */
class CustomTextView(context:Context, attributes: AttributeSet, def:Int) :
    AppCompatTextView(context, attributes, def) {


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = 0f
        var y = 0f
        var lastX = 0f
        var lastY = 0f
        event?.let {
            val action = event.action
            when(action){
                MotionEvent.ACTION_DOWN->{
                    x = event.x
                    y = event.y
                }
                MotionEvent.ACTION_MOVE ->{
                    lastX = x
                    lastY = y
                }
                MotionEvent.ACTION_UP->{

                }
            }
            LogUtils.e("x = $x, y = $y,  lastX = $lastX, lastY = $lastY")
        }
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }
}