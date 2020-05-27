package cn.wxb.kt.widget

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import cn.wxb.manager.ActivityManager
import com.blankj.utilcode.util.LogUtils

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/26 15:17
 */
class CustomObserver(private val TAG : String = "CustomObserver") : LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(){
        LogUtils.w(TAG, "---- onAny ----")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        LogUtils.w(TAG, "---- onCreate ----")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        LogUtils.w(TAG, "---- onStart ----")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        LogUtils.w(TAG, "---- onResume ----")
   }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        LogUtils.w(TAG, "---- onPause ----")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        LogUtils.w(TAG, "---- onStop ----")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        LogUtils.w(TAG, "---- onDestroy ----")
    }
}