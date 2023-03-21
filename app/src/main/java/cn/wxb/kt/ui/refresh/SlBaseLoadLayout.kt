package cn.wxb.kt.ui.refresh

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import cn.wxb.kt.R

/**
 * @desc: 上拉加载
 * @author: wuxiaobo
 * @date: 2022/4/24 09:37
 */
open class SlBaseLoadLayout : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    protected var tvNotice: TextView? = null
    protected var lottieView: LottieAnimationView? = null

    private fun initView(context: Context) {
        val view = inflate(context, R.layout.sl_load_layout, this)
        tvNotice = view.findViewById(R.id.tv_loading_show_notice)
        lottieView = view.findViewById(R.id.loading_lottie_view)
    }

    protected fun changeText(text: String) {
        tvNotice?.text = text
    }

    protected fun pauseAnimation() {
        lottieViewIsVisible(true)
        lottieView?.pauseAnimation()
    }

    protected fun cancelAnimation() {
        lottieViewIsVisible(true)
        lottieView?.cancelAnimation()
    }

    protected fun playAnimation() {
        lottieViewIsVisible(true)
        lottieView?.playAnimation()
    }

    protected fun lottieViewIsVisible(isVisible: Boolean) {
        if (lottieView?.isVisible == isVisible) {
            return
        }
        lottieView?.isVisible = isVisible
    }

}