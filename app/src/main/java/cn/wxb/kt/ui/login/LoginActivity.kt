package cn.wxb.kt.ui.login

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import cn.wxb.kt.R
import cn.wxb.kt.databinding.ActivityLoginBinding
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.ui.MainActivity
import cn.wxb.kt.ui.home.activity.MainActivityV2
import kotlinx.android.synthetic.main.activity_login.*
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
        btnLogin.setOnClickListener {
            val map: MutableMap<String, String> = HashMap()
            map["grant_type"] = "password"
            map["device_token"] = "1111111111111111111"
            map["username"] = "13651269612"
            map["password"] = "111111"
            map["device_type"] = "android"

            viewModel.getLoginToken(map).observe(this, Observer {
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
        finish()
    }
}
