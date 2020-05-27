package cn.wxb.kt.ui.home.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cn.wxb.kt.MainActivity

import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.mvvm.base.NoViewModel
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import cn.wxb.kt.widget.CustomObserver
import cn.wxb.manager.ActivityManager
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<MainViewModel, ViewDataBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun lazyLoadData() {
        init()
//        lifecycle.addObserver(CustomObserver())//测试生命周期监听
    }

    override fun isShareVM() = true

    private fun init(){
        LogUtils.e(viewModel.name.value)
        btnModifyVmValue.setOnClickListener {
            viewModel.name.value = "java"
        }

        viewModel.name.observe(activity!!, Observer {
            LogUtils.e("---------------------- ${viewModel.name.value}")
        })
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}
