package cn.wxb.kt.ui.home.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import cn.wxb.kt.R
import cn.wxb.kt.databinding.FragmentHomeBinding
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.ui.home.activity.PatientListActivity
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import cn.wxb.kt.ui.mine.activity.MineOrderActivity
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<MainViewModel, FragmentHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    //与Activity共享viewModel
    override fun isShareVM() = true

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.vm = viewModel
        viewModel.name.observe(this, Observer {
            LogUtils.e("============= ${mBinding?.vm?.name}")
        })

    }
    override fun lazyLoadData() {
        LogUtils.e(">>>>>>>>>>>>>> lazyLoadData")
        init()
    }

    override fun show() {
        LogUtils.e("------------ fragment show ---------------")
    }

    private fun init(){
        btnModifyVmValue.setOnClickListener {
            viewModel.name.value = "java"
        }

        tvContent.setOnClickListener {
//            PatientListActivity.actionStart(activity!!)
            MineOrderActivity.actionStart(activity!!)
        }
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
