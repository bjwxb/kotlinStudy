package cn.wxb.kt.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import cn.wxb.kt.R
import cn.wxb.kt.databinding.FragmentMineBinding
import cn.wxb.kt.service.TestService
import cn.wxb.kt.ui.home.viewmodel.MineViewModel
import cn.wxb.kt.ui.mine.activity.PatientCroListActivity
import cn.wxb.kt.widget.CustomObserver
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mine.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class MineFragment : Fragment() {

    private lateinit var viewModel:MineViewModel
    private lateinit var mBinding:FragmentMineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MineViewModel::class.java]
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)
        mBinding.vm =viewModel
        mBinding.lifecycleOwner = this
//        lifecycle.addObserver(CustomObserver("mine"))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
    }

    fun init(view:View){
        tvMineContent.setOnClickListener {
            viewModel.updateTitle("ok")
            PatientCroListActivity.actionStart(activity!!)
        }
        tvTitle.setOnClickListener {
            viewModel.updateTitle("python")
            TestService.enqueueWork(context, Intent())
        }
        viewModel.title.observe(viewLifecycleOwner, Observer {
            //ToastUtils.showShort(viewModel.title.value)
        })
    }

    companion object {
        fun newInstance() = MineFragment()
    }
}
