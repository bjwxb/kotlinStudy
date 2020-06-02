package cn.wxb.kt.data.repo

import cn.wxb.kt.data.db.dao.PatientInfoDao
import cn.wxb.kt.mvvm.base.BaseViewModel
import cn.wxb.kt.network.entity.PatientInfo

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/29 14:32
 */
class MainRepository (private val patientInfoLocalData: PatientInfoDao):BaseViewModel(){

    suspend fun getPatientInfo(): PatientInfo {
        return patientInfoLocalData.getPatientInfo()
    }

    //双重检查锁单例实现
    companion object{
        @Volatile private var INSTANCE: MainRepository? = null

        //如果 ?: 左侧表达式非空，就返回其左侧表达式，否则返回右侧表达式
        fun getInstance(patientInfoLocalData: PatientInfoDao) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MainRepository(patientInfoLocalData).also { INSTANCE = it }
            }
    }
}