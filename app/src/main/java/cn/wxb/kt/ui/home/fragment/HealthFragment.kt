package cn.wxb.kt.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2

import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.mvvm.base.NoViewModel
import cn.wxb.kt.ui.home.adapter.VpMainRvAdapter
import kotlinx.android.synthetic.main.fragment_health.*

class HealthFragment : BaseFragment<NoViewModel, ViewDataBinding>() {


    override fun layoutId() = R.layout.fragment_health

    override fun initView(savedInstanceState: Bundle?) {
        initVp()
    }

    private fun initVp(){
        val list = mutableListOf<String>("python", "kotlin", "java")
        vpTestRv.adapter = VpMainRvAdapter(list)
        vpTestRv.orientation = ViewPager2.ORIENTATION_VERTICAL
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HealthFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}
