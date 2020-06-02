package cn.wxb.kt.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import cn.wxb.kt.R
import cn.wxb.kt.databinding.ActivityMainBinding
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.ui.home.fragment.HomeFragment
import cn.wxb.kt.ui.home.fragment.MedicFragment
import cn.wxb.kt.ui.home.fragment.MineFragment
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
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val homeFragment = HomeFragment.newInstance()
    private val medicFragment = MedicFragment.newInstance()
    private val mineFragment = MineFragment.newInstance()

    companion object {
        fun actionStart(context:Context) = Intent().let{
            it.setClass(context, MainActivity::class.java)
            context.startActivity(it)
        }
    }

    override fun layoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        lifecycle.addObserver(CustomObserver())//测试activity生命周期监听
        mBinding?.vm = viewModel

//        viewModel.getPatientInfo()
    }

    override fun initData() {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.flContainer, homeFragment)
        transaction.add(R.id.flContainer, medicFragment)
        transaction.add(R.id.flContainer, mineFragment)
        transaction.commit()
        showHomeFragment()

        tvHome.setOnClickListener{
            showHomeFragment()
        }

        tvMedic.setOnClickListener {
            showMedicFragment()
        }

        tvMine.setOnClickListener {
            showMineFragment()
        }

        tvName.setOnClickListener {
        }

        viewModel.name.observe(this, Observer {
            ToastUtils.showShort(viewModel.name.value)
        })


    }


    fun showHomeFragment(){
        hideFragment()
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.show(homeFragment)
        transaction.commit()
    }

    fun showMedicFragment(){
        hideFragment()
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.show(medicFragment)
        transaction.commit()
    }

    fun showMineFragment(){
        hideFragment()
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.show(mineFragment)
        transaction.commit()
    }

    fun hideFragment(){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.hide(homeFragment)
        transaction.hide(medicFragment)
        transaction.hide(mineFragment)
        transaction.commit()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
