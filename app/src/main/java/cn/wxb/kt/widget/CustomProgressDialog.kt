package cn.wxb.kt.widget

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import cn.wxb.kt.R

/**
 * description:
 *
 * author: wuxiaobo
 * date: 2020/4/25 22:53
 */
class CustomProgressDialog : ProgressDialog {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, theme: Int) : super(context, theme) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCancelable(false)
        setCanceledOnTouchOutside(false)

        setContentView(R.layout.loading_dialog)
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params

    }
}