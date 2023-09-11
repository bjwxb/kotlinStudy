package cn.wxb.kt.ui.login

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.LogUtils

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2023/7/27 17:54
 */
class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        invalidate()
        LogUtils.e(">>>>>> $this")
        return false
    }
}