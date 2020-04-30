package cn.wxb.kt.mvvm.app

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 15:04
 */
object MVVM {

    private lateinit var mConfig: GlobalConfig

    fun install(config: GlobalConfig) {
        mConfig = config
    }

    fun getConfig() = mConfig

}