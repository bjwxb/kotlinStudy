package cn.wxb.kt.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.wxb.kt.data.db.XzlDatabase
import cn.wxb.kt.data.repo.MainRepository
import cn.wxb.kt.mvvm.base.BaseViewModel
import cn.wxb.kt.network.entity.PatientInfo

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/22 14:04
 */
class MainViewModel : BaseViewModel(){
    val name:MutableLiveData<String> = MutableLiveData()
    val student:MutableLiveData<Student> = MutableLiveData()

    val mPatientInfo:MutableLiveData<PatientInfo> = MutableLiveData()

    init {
        name.value = "Hello World"
        student.value = Student().apply {
            this.age = 11
            this.name = "July"
        }
    }

    val mainRepository by lazy {
        MainRepository.getInstance(XzlDatabase.getInstance().patientInfoLocalData())
    }
//
    fun getPatientInfo():MutableLiveData<PatientInfo>{
        launchGo({
            mPatientInfo.value = mainRepository.getPatientInfo()
        })
        return mPatientInfo
    }

    override fun onCleared() {
        super.onCleared()
    }
}

class Student{
    var name = ""
    var age = 10
}