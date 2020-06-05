package cn.wxb.kt.data.http

import cn.wxb.kt.network.api.MineService
import cn.wxb.kt.util.RetrofitClient

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 15:41
 */
class MineNetwork {
    private val mService by lazy { RetrofitClient.getInstance().create(MineService::class.java) }

    object Holder{
        val INSTANCE = MineNetwork()
    }
    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    suspend fun getMineOrderList(map:HashMap<String, String>) = mService.getMineOrderList(map)

}