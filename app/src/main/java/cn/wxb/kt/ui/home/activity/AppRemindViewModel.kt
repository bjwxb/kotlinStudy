package cn.wxb.kt.ui.home.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.wxb.kt.mvvm.base.BaseViewModel

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/26 9:41
 */
@Suppress("UNCHECKED_CAST")
class AppRemindViewModel : ViewModel(){
    public var name:MutableLiveData<String> = MutableLiveData()


    class AppRemindViewModelFactory : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.newInstance()
        }

    }
}