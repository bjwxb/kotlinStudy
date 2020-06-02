package cn.wxb.kt.network.api

import cn.wxb.kt.app.base.BaseResult
import cn.wxb.kt.common.Constant
import cn.wxb.kt.network.entity.BaseResponse
import cn.wxb.kt.network.entity.DoctorBean
import cn.wxb.kt.network.entity.LoginToken
import cn.wxb.kt.network.entity.PatientInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 11:37
 */
interface LoginService {
    //获取授权token
    @FormUrlEncoded
    @POST(Constant.URL.OAUTH_TOKEN)
    suspend fun getToken(@FieldMap map: Map<String, String>): LoginToken

    //获取授权token
    @FormUrlEncoded
    @POST(Constant.URL.OAUTH_TOKEN)
    fun getToken2(@FieldMap map: Map<String, String>): Observable<LoginToken>

    //    //登录后获取医生相关信息
    @GET(Constant.URL.PATIENT_INFO)
    suspend fun getPatientInfo(): BaseResult<PatientInfo>
}