package cn.wxb.kt.network.api

import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.common.Constant
import cn.wxb.kt.network.entity.OrderBean
import cn.wxb.kt.network.entity.PatientCroBean
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 11:49
 */
interface MineService {

    //获取临床试验cro项目列表
    @GET(Constant.URL.PATIENT_CRO_LIST)
    suspend fun getPatientCroList(): BaseResult<PatientCroBean>

    //获取我的订单列表

    @GET(Constant.URL.MINE_ORDER_LIST)
    suspend fun getMineOrderList(@QueryMap map:HashMap<String, String>): BaseResult<OrderBean>
}