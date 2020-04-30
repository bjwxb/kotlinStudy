package cn.wxb.kt.mvvm.base

import android.app.Application
import android.content.Context
import cn.wxb.kt.mvvm.app.GlobalConfig
import cn.wxb.kt.mvvm.app.MVVM

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 14:54
 */
open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MVVM.install(GlobalConfig().apply {
            viewModelFactory = ViewModelFactory()
        })
    }
}