package cn.wxb.kt.ui.login

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import cn.wxb.kt.R
import cn.wxb.kt.databinding.ActivityLoginBinding
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.network.bean.request.LoginRequestBean
import cn.wxb.kt.ui.home.activity.MainActivityV2
import com.blankj.utilcode.util.LogUtils
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
                JSONObject(map).toString()
            )

            val bean = LoginRequestBean()
            bean.deviceToken = "11111111111"
            bean.account = "13651269612"
            bean.password = "123456"
            bean.deviceType = "ANDROID"

            viewModel.getLoginToken(requestBody).observe(this, Observer {
                LogUtils.e(it)
                jump2Main()
            })
        }

        etPhone.addTextChangedListener{
            viewModel.modifyPhone(it.toString())
        }

    }

    private fun jump2Main(){
//        MainActivity.actionStart(this)
        MainActivityV2.actionStart(this)
//        finish()
    }
}
