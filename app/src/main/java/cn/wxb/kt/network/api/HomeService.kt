package cn.wxb.kt.network.api

import cn.wxb.kt.common.Constant
import cn.wxb.kt.network.entity.LoginToken
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 16:55
 */
interface HomeService {

    //获取授权token
    @FormUrlEncoded
    @POST(Constant.URL.OAUTH_TOKEN)
    fun getToken(@FieldMap map: Map<String, String>): Observable<LoginToken>

//    //登录后获取医生相关信息
//    @GET(Constant.URL.DOCTOR_USER)
//    fun getDoctorUser(): Observable<BaseResponse<DoctorBean?>?>?
//
//    //获取患者列表
//    @GET(Constant.URL.DOCTOR_PATIENT_LIST)
//    fun getPatientList(@QueryMap map: Map<String?, String?>?): Observable<BaseResponse<PatientInfoBean?>?>?
//
//    //医生的临床试验项目列表
//    @GET(Constant.URL.DOCTOR_CRO_PROJECT_LIST)
//    fun getDoctorCroList(@Path("userId") userId: String?): Observable<BaseResponse<DoctorCroProjectBean?>?>?
}