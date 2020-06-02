package cn.wxb.kt.data.repo

import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.data.db.XzlDatabase
import cn.wxb.kt.data.db.dao.DoctorBeanDao
import cn.wxb.kt.data.db.dao.LoginTokenDao
import cn.wxb.kt.data.db.dao.PatientInfoDao
import cn.wxb.kt.data.http.LoginNetwork
import cn.wxb.kt.mvvm.base.BaseModel
import cn.wxb.kt.network.entity.DoctorBean
import cn.wxb.kt.network.entity.LoginToken
import cn.wxb.kt.network.entity.PatientInfo
import com.blankj.utilcode.util.LogUtils

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 11:53
 */
class LoginRepository(private val netWork: LoginNetwork,
                      private val loginTokenLocalData : LoginTokenDao,
                      private val patientInfoLocalData :PatientInfoDao):BaseModel() {

    suspend fun getLoginToken(map:Map<String, String>): LoginToken {
        val token = netWork.getLoginToken(map)
        loginTokenLocalData.insertData(token)
        return token
    }

    suspend fun getPatientInfo(): BaseResult<PatientInfo> {
        val ret = netWork.getPatientInfo()
        patientInfoLocalData.insertData(ret.data)
        return netWork.getPatientInfo()
    }

    companion object{

        @Volatile private var INSTANCE: LoginRepository? = null

        fun getInstance(netWork: LoginNetwork, loginTokenLocalData : LoginTokenDao, patientInfoLocalData: PatientInfoDao) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepository(netWork, loginTokenLocalData, patientInfoLocalData).also { INSTANCE = it }
            }
    }

}