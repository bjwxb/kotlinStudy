package cn.wxb.kt.ui.refresh

import android.content.Context
import android.util.AttributeSet
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author: caizenghui
 * @date: 2021-03-08 10:10 AM Monday
 */
open class NevRefreshLayout : SmartRefreshLayout {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    open fun init() {
//        setRefreshHeader(SlRefreshHeader(context))
//        setRefreshFooter(SlLoadFooter(context))
        setEnableOverScrollBounce(false)
        setEnableOverScrollDrag(false)
    }
}