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
class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("wxb", "textView onTouchEvent >>>>>>>> ${event?.action}")
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
                    LogUtils.d("wxb_tv", ">>>>>> down")
                }
                MotionEvent.ACTION_MOVE ->{
                    lastX = x
                    lastY = y
                    LogUtils.d("wxb_tv", ">>>>>> move move")
                }
                MotionEvent.ACTION_UP->{
                    LogUtils.d("wxb_tv", "======== up")
                }
                MotionEvent.ACTION_CANCEL -> {
                    //ex: recyclerview item 按下/横向滑动都可以收到down/move事件，上下滑动时，rv接管，tv收到cancel事件
                    LogUtils.d("wxb_tv", ">>>>>>>   cancel <<<<<<<")
                }
            }
            LogUtils.d("x = $x, y = $y,  lastX = $lastX, lastY = $lastY")
        }
//        return super.onTouchEvent(event)
        return true//消费事件，不会向上冒泡
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("wxb",">>>> TextView dispatchTouchEvent <<<<<<")
        return super.dispatchTouchEvent(event)
//        return true//消费事件
//        return false//交给viewGroup的onTouchEvent
    }
}