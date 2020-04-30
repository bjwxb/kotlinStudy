package cn.wxb.kt.app

import cn.wxb.kt.mvvm.base.BaseApplication
import com.blankj.utilcode.util.LogUtils

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 15:11
 */
class MyApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.getConfig().run {
//            isLogSwitch = .DEBUG
            setSingleTagSwitch(true)
        }
    }
}