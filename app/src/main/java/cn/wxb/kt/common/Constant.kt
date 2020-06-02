package cn.wxb.kt.common

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 16:30
 */
object Constant {
    const val BASE_URL = "https://api.test.xzlcorp.com/v0/"

    object URL {
        //login token
        const val OAUTH_TOKEN = "oauth/token"
        //获取患者信息
        const val PATIENT_INFO = "patient/init"
        //获取患者列表
        const val DOCTOR_PATIENT_LIST = "doctor/user/patient"
        //临床试验项目列表
        const val DOCTOR_CRO_PROJECT_LIST = "doctor/clinical/project/doctor/{userId}"
    }

    object HttpCode {
        /**
         * 请求成功
         */
        const val HTTP_STATUS_SUCCESS = "success"
        const val HTTP_STATUS_FAIL = "fail"
        /**
         * 登录失败
         */
        const val HTTP_CODE_LOGIN_TOKEN_FAILED = 400
        /**
         * 未登录
         */
        const val HTTP_CODE_WITHOUT_LOGIN = 401
        /**
         * 服务器错误
         */
        const val HTTP_CODE_SERVER_ERROR = 500
    }
}