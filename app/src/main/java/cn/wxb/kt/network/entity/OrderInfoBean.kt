package cn.wxb.kt.network.entity

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 15:38
 */
data class OrderBean(
    val orderApiInfo: MutableList<OrderApiInfo>?,
    val total: Int?
)

data class OrderApiInfo(
    val count: Int?,
    val createdAt: Long?,
    val goodsCategory: String?,
    val goodsDetailType: String?,
    val goodsName: String?,
    val orderId: String?,
    val orderNo: String?,
    val orderStatus: String?,
    val patientIllnessStatus: Boolean?,
    val subject: String?,
    val total: String?
)