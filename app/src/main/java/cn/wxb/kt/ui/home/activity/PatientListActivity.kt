package cn.wxb.kt.ui.home.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.mvvm.base.NoViewModel

/**
 * 描述: 患者列表
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/28 10:25
 */
class PatientListActivity : BaseActivity<NoViewModel, ViewDataBinding>() {

    companion object {
        fun actionStart(context: Context){
            Intent().let {
                it.setClass(context, PatientListActivity::class.java)
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list)
    }

    override fun layoutId() = R.layout.activity_patient_list

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {

    }
}
