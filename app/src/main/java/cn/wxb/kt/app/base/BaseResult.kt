package cn.wxb.kt.app.base

import cn.wxb.kt.mvvm.base.IBaseResponse

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 15:13
 */
data class BaseResult<T>(
        val errorMsg: String,
        val status: String,
        val data: T
) : IBaseResponse<T> {

    override fun status() = status

    override fun message() = errorMsg

    override fun data() = data

    override fun isSuccess() = status == "success"

}