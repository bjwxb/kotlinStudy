package cn.wxb.kt.common

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 16:30
 */
object Constant {
    const val BASE_URL = "https://napi.xinzhili.cn/"

    object URL {
        //login token
        const val OAUTH_TOKEN = "user/token"
        //获取患者信息
        const val PATIENT_INFO = "patient/init"
        //CRO临床试验列表
        const val PATIENT_CRO_LIST = "patient/clinical/project"
        //我的订单列表
        const val MINE_ORDER_LIST = "patient/business/order"
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