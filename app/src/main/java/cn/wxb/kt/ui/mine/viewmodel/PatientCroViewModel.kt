package cn.wxb.kt.ui.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.data.db.XzlDatabase
import cn.wxb.kt.data.http.LoginNetwork
import cn.wxb.kt.data.http.PatientCroNetwork
import cn.wxb.kt.data.repo.LoginRepository
import cn.wxb.kt.data.repo.PatientCroRepository
import cn.wxb.kt.mvvm.base.BaseViewModel
import cn.wxb.kt.network.entity.PatientCroBean
import cn.wxb.kt.network.entity.PatientInfo
import cn.wxb.kt.util.InjectorUtil

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 11:57
 */
class PatientCroViewModel : BaseViewModel() {

    private val loginRepository by lazy {
        PatientCroRepository.getInstance(
            PatientCroNetwork.getInstance()
        )
    }

    private val mPatientCroBean = MutableLiveData<PatientCroBean>()

    fun getPatientCroBean(): MutableLiveData<PatientCroBean> {
        launchGo({
            mPatientCroBean.value = loginRepository.getPatientCroList().data
        })
        return mPatientCroBean
    }
}