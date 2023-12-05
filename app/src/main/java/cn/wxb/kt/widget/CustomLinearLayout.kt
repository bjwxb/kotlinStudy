package cn.wxb.kt.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils

/**
 * @desc:
 *
 * 甲 x
 * 乙 y
 * 2x + 2.5(x + y) = 36
 * 4.5x + 2.5y = 36
 * 9x + 5y = 72
 *
 * 9x + 36 - 3x = 72
 * 6x = 36
 * x = 6
 *
 * 5y + 3x = 36
 * y = 18 / 5 = 3.6
 * 2y + 3(x + y) = 36
 *
 * @author: wuxiaobo
 * @date: 2022/5/22 17:44
 */
class CustomLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.e("wxb",">>=====>> ViewGroup dispatchTouchEvent <<<======<<<")
//        return true//消费事件
//        return false//不分发事件，交由上级onTouchEvent处理
//        if(ev?.action != MotionEvent.ACTION_DOWN){
//            return false
//        }
        return super.dispatchTouchEvent(ev)//交由自己的onInterceptTouchEvent处理
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.e("wxb","****** ViewGroup onInterceptTouchEvent <<<<<<")
//        return super.onInterceptTouchEvent(ev)
        return false//同super,交给子view的dispatchTouchEvent处理
//        return true//交给自己的onTouchEvent
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("wxb","____------- ViewGroup onTouchEvent <<<<<<")
        return super.onTouchEvent(event)
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

}