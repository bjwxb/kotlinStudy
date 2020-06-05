package cn.wxb.kt.data.http

import cn.wxb.kt.network.api.LoginService
import cn.wxb.kt.network.api.MineService
import cn.wxb.kt.util.RetrofitClient

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 11:53
 */
class PatientCroNetwork {
    private val mService by lazy { RetrofitClient.getInstance().create(MineService::class.java) }


    suspend fun getPatientCroList() = mService.getPatientCroList()

    companion object {
        private var network: PatientCroNetwork? = null

        fun getInstance() = network ?: synchronized(this){
            network ?: PatientCroNetwork().also { network = it }
        }
    }
}