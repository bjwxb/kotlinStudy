package cn.wxb.kt.mvvm.base

/**
 * 描述: 网络请求响应基类
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 11:51
 */
interface IBaseResponse<T> {
    fun status(): String
    fun message(): String
    fun data(): T
    fun isSuccess(): Boolean
}