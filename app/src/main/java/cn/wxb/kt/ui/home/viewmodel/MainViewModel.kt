package cn.wxb.kt.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.wxb.kt.mvvm.base.BaseViewModel
import com.blankj.utilcode.util.LogUtils

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/22 14:04
 */
class MainViewModel : BaseViewModel(){
    val name:MutableLiveData<String> = MutableLiveData()

    init {
        name.value = "Hello World"
    }

    override fun onCleared() {
        super.onCleared()
    }
}