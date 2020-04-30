package cn.wxb.kt.mvvm.app

import androidx.lifecycle.ViewModelProvider
import cn.wxb.kt.mvvm.base.ViewModelFactory

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 15:03
 */
class GlobalConfig {
    var viewModelFactory: ViewModelProvider.NewInstanceFactory = ViewModelFactory()
}