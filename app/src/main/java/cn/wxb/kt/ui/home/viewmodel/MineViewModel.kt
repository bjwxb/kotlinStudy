package cn.wxb.kt.ui.home.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/28 14:02
 */
class MineViewModel : ViewModel(),LifecycleObserver{
    var title:MutableLiveData<String> = MutableLiveData("我的")

    fun updateTitle(title: String){
        this.title.value = title
    }

}