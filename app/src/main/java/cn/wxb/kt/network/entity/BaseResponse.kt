package cn.wxb.kt.network.entity

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 11:38
 */
data class BaseResponse<T>(val status:String, val data:T)