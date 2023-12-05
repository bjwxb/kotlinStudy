package cn.wxb.kt.ui.login

import android.content.Intent
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import cn.wxb.kt.R
import cn.wxb.kt.databinding.ActivityLoginBinding
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.network.bean.request.LoginRequestBean
import cn.wxb.kt.ui.home.activity.MainActivityV2
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*


/**
 * 描述: 登录页面
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 9:58
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun layoutId() = R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.vm = viewModel
    }

    override fun initData() {
        val str = btnLogin.text
        btnLogin.setOnClickListener {
            val map: MutableMap<String, String> = HashMap()
            map["deviceToken"] = "1111111111111111111"
            map["account"] = "13651269612"
            map["password"] = "123456"
            map["deviceType"] = "ANDROID"
            map["authType"] = "password_type"
            val requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                GsonUtils.toJson(map)
            )

            val bean = LoginRequestBean()
            bean.deviceToken = "11111111111"
            bean.account = "13651269612"
            bean.password = "123456"
            bean.deviceType = "ANDROID"


//            viewModel.getLoginToken(requestBody).observe(this, Observer {
//                LogUtils.e(it)
                jump2Main()
//            })

        }

        etPhone.addTextChangedListener{
            viewModel.modifyPhone(it.toString())
        }

        val m = android.graphics.Matrix()
        val points = floatArrayOf(10f, 10f)

        //pre 从下到上执行， post 从上到下顺序执行， set-跳过之前到设置，相当于reset，之后到设置生效
        m.preTranslate(8f, 7f)
        m.preScale(2f, 3f)
        m.mapPoints(points)
        println(">>wxb>> ${points[0]}, ${points[1]}")
    }

    /**
     * 530 40
     * 380 30
     *
     * (530-380)/10
     *
     *
     */
    private fun jump2Main(){
//        MainActivity.actionStart(this)
        MainActivityV2.actionStart(this)
//        finish()
//        val list = ArrayList<Int>()
//        val gifName = "ic_short_video_sticker_gif1"
//        val gifId: Int = resources.getIdentifier(gifName, "drawable", packageName)
//        val drawable = resources.getDrawable(gifId)
//        LogUtils.e("wxb", ">>>> $drawable")
//        mBinding?.ivGif?.setImageDrawable(drawable)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            if(drawable is AnimatedImageDrawable){
////                drawable.repeatCount = 1
////                drawable.start()
//                drawable as BitmapDrawable
//            }
//        }
//        LogUtils.e(">>>>>navigationHeight = ${getNavigationHeight()} <<<")
    }

    private fun getNavigationHeight():Int {
        //decorview = statusBar + content + navigationBar
        val decorViewHeight: Int = window.decorView.height

        val rootView = findViewById<ViewGroup>(android.R.id.content)
        val contentHeight: Int = rootView.height

        var height = 0
        val resourceId = applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            height = applicationContext.resources.getDimensionPixelSize(resourceId)
        }

        LogUtils.e(">>>> decorViewHeight = $decorViewHeight, contentH = $contentHeight, statusBarH = $height")
        return decorViewHeight - contentHeight - height
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        LogUtils.e("---------------- $requestCode >>>> $resultCode")
    }

    var time = 0L
    override fun onPause() {
        super.onPause()
        time = System.currentTimeMillis()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e(">>>>>> time = ${System.currentTimeMillis() - time}")
    }

}
