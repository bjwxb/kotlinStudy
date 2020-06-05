package cn.wxb.kt.ui.mine.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wxb.kt.R
import cn.wxb.kt.databinding.ActivityPatientCroListBinding
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.network.entity.ProjectInfo
import cn.wxb.kt.ui.mine.adapter.PatientCroListAdapter
import cn.wxb.kt.ui.mine.viewmodel.PatientCroViewModel
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.activity_patient_cro_list.*

/**
 * 描述: 临床试验项目列表
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 14:00
 */
class PatientCroListActivity : BaseActivity<PatientCroViewModel, ActivityPatientCroListBinding>() {

    private var mCroList = mutableListOf<ProjectInfo>()

    companion object{
        fun actionStart(context: Context){
            context.startActivity(Intent().apply {
                setClass(context, PatientCroListActivity::class.java)
            })
        }
    }

    override fun layoutId() = R.layout.activity_patient_cro_list

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.vm = viewModel
        initRv()
    }

    private fun initRv(){
        rvCro.adapter = PatientCroListAdapter(mCroList)
        rvCro.layoutManager = LinearLayoutManager(this)
    }

    override fun initData() {
        viewModel.getPatientCroBean().observe(this, Observer {
            it.projectInfoList?.let {
                mCroList.addAll(it)
                rvCro.adapter?.notifyDataSetChanged()
            }
        })
    }
}
