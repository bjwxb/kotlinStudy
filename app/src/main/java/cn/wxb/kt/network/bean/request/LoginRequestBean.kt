package cn.wxb.kt.network.bean.request

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2021/6/2 9:59 上午
 */
data class LoginRequestBean(
    var account: String = "",
    var authType: String = "",
    var deviceToken: String = "",
    var deviceType: String = "",
    var password: String = ""
)