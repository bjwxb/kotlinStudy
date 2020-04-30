package cn.wxb.kt.mvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 14:53
 */
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /* val type = modelClass.constructors[0].parameterTypes
         if (type.isNotEmpty()) {
             val tClass = type[0]
             if (HomeRepository::class.java.isAssignableFrom(tClass)) {
                 return modelClass.getConstructor(tClass).newInstance(Injection.HomeRepository())
             } else if (XXXRepository::class.java.isAssignableFrom(tClass)) {
                 return modelClass.getConstructor(tClass).newInstance(Injection.XXXRepository())
             }
         }*/
        return modelClass.newInstance()
    }
}