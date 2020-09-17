package cn.wxb.kt.ui.home.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.mvvm.base.NoViewModel
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_medic.*


/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/5 11:02
 */
class MedicFragment : BaseFragment<NoViewModel, ViewDataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun layoutId() = R.layout.fragment_medic


    override fun initView(savedInstanceState: Bundle?) {
        initRv()

    }

    private fun initRv(){
        rvList.adapter
        rvList.layoutManager = LinearLayoutManager(mContext)
    }

//    fun init(view:View){
////       val vm = ViewModelProviders.of(activity!!).get(AppRemindViewModel::class.java)
////        val vm = ViewModelProvider(activity!!)[AppRemindViewModel::class.java]
//        val vm = ViewModelProvider(activity!!)[MainViewModel::class.java]
//    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MedicFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}
