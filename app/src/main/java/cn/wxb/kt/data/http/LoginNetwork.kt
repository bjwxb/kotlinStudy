package cn.wxb.kt.data.http

import cn.wxb.kt.network.api.HomeService
import cn.wxb.kt.network.api.LoginService
import cn.wxb.kt.util.RetrofitClient

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 11:35
 */
class LoginNetwork {
    private val mService by lazy { RetrofitClient.getInstance().create(LoginService::class.java) }

    suspend fun getLoginToken(map: Map<String, String>) = mService.getToken(map)

    suspend fun getPatientInfo() = mService.getPatientInfo()

    companion object {
        private var network: LoginNetwork? = null

        fun getInstance() = network ?: synchronized(this){
            network ?: LoginNetwork().also { network = it }
        }
    }
}