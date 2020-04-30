package cn.wxb.kt.mvvm.network

import cn.wxb.kt.mvvm.base.IBaseResponse

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 11:55
 */
class ResponseThrowable : Exception{

    var errMsg: String

    constructor(error: Error, e: Throwable? = null) : super(e) {
        errMsg = error.getValue()
    }

    constructor(msg: String, e: Throwable? = null) : super(e) {
        this.errMsg = msg
    }

    constructor(base: IBaseResponse<*>, e: Throwable? = null) : super(e) {
        this.errMsg = base.message()
    }
}