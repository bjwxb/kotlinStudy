package cn.wxb.kt.ui.login

import androidx.lifecycle.MutableLiveData
import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.data.db.dao.LoginTokenDao
import cn.wxb.kt.mvvm.base.BaseViewModel
import cn.wxb.kt.network.api.LoginService
import cn.wxb.kt.network.entity.DoctorBean
import cn.wxb.kt.network.entity.LoginToken
import cn.wxb.kt.util.InjectorUtil
import cn.wxb.kt.util.RetrofitClient
import com.blankj.utilcode.util.LogUtils

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 10:35
 */
class LoginViewModel : BaseViewModel(){

    var count = 0;
    //变量初始化方法1  立即初始化
    var phone : MutableLiveData<String> = MutableLiveData("")

    fun modifyPhone(phone: String){
        this.phone.value = phone
    }

    private val loginRepository by lazy {
        InjectorUtil.getLoginRepository()
    }

    private val mLoginToken = MutableLiveData<LoginToken>()

    private val mDoctorBean = MutableLiveData<BaseResult<DoctorBean>>()

    fun getLoginToken(map:Map<String, String>): MutableLiveData<BaseResult<DoctorBean>>{
        launchGo({
            mLoginToken.value = loginRepository.getLoginToken(map)

            mDoctorBean.value = loginRepository.getDoctorInfo()
        })
        return mDoctorBean
    }

    fun getDoctorBean(): MutableLiveData<BaseResult<DoctorBean>>{
        launchGo({
            mDoctorBean.value = loginRepository.getDoctorInfo()
        })
        return mDoctorBean
    }

}