package cn.wxb.kt.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils

/**
 * @desc:
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
}