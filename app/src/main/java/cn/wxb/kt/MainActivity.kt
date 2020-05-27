package cn.wxb.kt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.ui.home.fragment.HomeFragment
import cn.wxb.kt.ui.home.fragment.MedicFragment
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import cn.wxb.kt.widget.CustomObserver
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 描述: 首页-包含数个fragment
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/8 10:04
 */
class MainActivity : BaseActivity<MainViewModel, ViewDataBinding>() {

    val homeFragment = HomeFragment.newInstance()
    val medicFragment = MedicFragment.newInstance()

    companion object {
        fun actionStart(context:Context) = Intent().let{
            it.setClass(context, MainActivity::class.java)
            context.startActivity(it)
        }
    }

    override fun layoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        lifecycle.addObserver(CustomObserver())//测试activity生命周期监听
    }

    override fun initData() {
        replaceFragment(homeFragment)

        tvHome.setOnClickListener{
//            replaceFragment(homeFragment)
            viewModel.name.value = "首页----"
        }

        viewModel.name.observe(this, Observer {
            ToastUtils.showShort(viewModel.name.value)
        })


        tvMedic.setOnClickListener {
//            replaceFragment(medicFragment)
            viewModel.name.value = "服药----"
        }
    }

    fun replaceFragment(fragment:Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.flContainer, homeFragment)
        transaction.add(R.id.flContainerMedic, medicFragment)
        transaction.commit()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
