package cn.wxb.kt.ui.home.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.mvvm.base.NoViewModel
import cn.wxb.kt.ui.home.activity.AppRemindActivity
import cn.wxb.kt.ui.home.activity.AppRemindViewModel
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 * Use the [MedicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedicFragment : BaseFragment<NoViewModel, ViewDataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun layoutId() = R.layout.fragment_medic


    override fun initView(savedInstanceState: Bundle?) {


    }

//    fun init(view:View){
////       val vm = ViewModelProviders.of(activity!!).get(AppRemindViewModel::class.java)
////        val vm = ViewModelProvider(activity!!)[AppRemindViewModel::class.java]
//        val vm = ViewModelProvider(activity!!)[MainViewModel::class.java]
//        val btn = view.findViewById<Button>(R.id.btnModifyVm)
//        btn.setOnClickListener {
//            vm.name.value = "kotlin"
//        }
//        val tvContent = view.findViewById<TextView>(R.id.tvContent)
//        vm.name.observe(activity!!, Observer {
//            tvContent.text = it
//        })
//
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
