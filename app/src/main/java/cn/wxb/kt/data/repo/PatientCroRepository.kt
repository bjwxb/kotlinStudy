package cn.wxb.kt.data.repo

import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.data.db.dao.LoginTokenDao
import cn.wxb.kt.data.db.dao.PatientInfoDao
import cn.wxb.kt.data.http.LoginNetwork
import cn.wxb.kt.data.http.PatientCroNetwork
import cn.wxb.kt.mvvm.base.BaseModel
import cn.wxb.kt.network.entity.PatientCroBean

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 11:52
 */
class PatientCroRepository(private val netWork: PatientCroNetwork) :BaseModel() {

    suspend fun getPatientCroList():BaseResult<PatientCroBean>{
        return netWork.getPatientCroList()
    }

    companion object{
        @Volatile private var INSTANCE: PatientCroRepository? = null

        fun getInstance(netWork: PatientCroNetwork) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PatientCroRepository(netWork).also { INSTANCE = it }
            }
    }
}