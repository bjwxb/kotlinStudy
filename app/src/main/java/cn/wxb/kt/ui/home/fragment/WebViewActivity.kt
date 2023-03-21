package cn.wxb.kt.ui.home.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.*
import androidx.databinding.ViewDataBinding
import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.mvvm.base.NoViewModel
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : BaseActivity<NoViewModel, ViewDataBinding>() {

    companion object {
        fun actionStart(context: Context){
            Intent().let {
                it.setClass(context, WebViewActivity::class.java)
                context.startActivity(it)
            }
        }
    }

    private var wm: WindowManager? = null
    private var fullScreenView: View? = null

    override fun layoutId() = R.layout.activity_web_view

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView(savedInstanceState: Bundle?) {
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
        wm = windowManager

        webView.settings.apply {
            javaScriptEnabled = true
        }

        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }

        webView.webChromeClient = object : WebChromeClient(){

            override fun onShowCustomView(view: View, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                // 此处的 view 就是全屏的视频播放界面，需要把它添加到我们的界面上
                wm?.addView(
                    view,
                    WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_APPLICATION)
                )
                LogUtils.e("============ show customView ===========")

                // 去除状态栏和导航按钮
                fullScreen(view)

                // 放开竖屏限制，设置为根据用户行为判断
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                fullScreenView = view
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                LogUtils.e(">>>>> hide customView <<<<<<")
                // 退出全屏播放，我们要把之前添加到界面上的视频播放界面移除
                wm?.removeViewImmediate(fullScreenView)

                //恢复屏幕限制为竖屏
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                fullScreenView = null
            }
        }

        val url = "https://www.bilibili.com/video/BV1UW411t7pe/"
//        val url = "https://mall-test.saloontech.cn/package-life/pages/productDetail?id=148"
        webView.loadUrl(url)
    }
    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        // 如果之前处于全屏状态，重新进入后需要再次调用全屏
        if (fullScreenView != null) fullScreen(fullScreenView!!)
    }


    /**
     * SYSTEM_UI_FLAG_LOW_PROFILE - 设置状态栏和导航栏中的图标变小，变模糊或者弱化其效果,点击状态栏和导航栏相应的位置，这些图标的效果会还原成正常的状态
     * SYSTEM_UI_FLAG_FULLSCREEN - 隐藏状态栏，点击屏幕区域不会出现，需要从状态栏位置下拉才会出现
     * SYSTEM_UI_FLAG_HIDE_NAVIGATION - 隐藏导航栏，点击屏幕任意区域，导航栏将重新出现，并且不会自动消失
     * SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION - 将布局内容拓展到导航栏的后面
     * SYSTEM_UI_FLAG_LAYOUT_STABLE - 稳定布局，主要是在全屏和非全屏切换时，布局不要有大的变化。一般和View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN、View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION搭配使用。同时，android:fitsSystemWindows要设置为true
     */
    private fun fullScreen(view: View) {
        if (Build.VERSION_CODES.KITKAT == Build.VERSION.SDK_INT) {
            view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        } else {
            view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}