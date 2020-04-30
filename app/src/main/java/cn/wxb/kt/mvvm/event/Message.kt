package cn.wxb.kt.mvvm.event

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 14:39
 */
class Message @JvmOverloads constructor(
        var code: Int = 0,
        var msg: String = "",
        var arg1: Int = 0,
        var arg2: Int = 0,
        var obj: Any? = null
)
