package cn.wxb.kt.data.repo

import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.data.http.MineNetwork
import cn.wxb.kt.data.http.PatientCroNetwork
import cn.wxb.kt.mvvm.base.BaseViewModel
import cn.wxb.kt.network.entity.OrderBean
import cn.wxb.kt.network.entity.PatientCroBean

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 15:40
 */
class MineRepository(private val netWork: MineNetwork) :BaseViewModel() {

    suspend fun getOrderList(map:HashMap<String, String>): BaseResult<OrderBean> {
        return netWork.getMineOrderList(map)
    }

    companion object{
        @Volatile private var INSTANCE: MineRepository? = null
        fun getInstance(netWork: MineNetwork) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MineRepository(netWork).also { INSTANCE = it }
            }
    }
}