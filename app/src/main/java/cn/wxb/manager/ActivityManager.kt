package cn.wxb.manager

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.*

/**
 * 描述:activity 栈管理
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/27 9:45
 */
class ActivityManager : Application.ActivityLifecycleCallbacks{
    private val activities: Stack<Activity> = Stack()
    private var _count = 0

    companion object{
        fun getInstance() = ActivityManagerHolder.INSTANCE
    }

    object ActivityManagerHolder{
        val INSTANCE = ActivityManager()
    }

    private fun addActivity(activity: Activity){
        activities.add(activity)
    }

    private fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    //finish 指定activity
    fun finishActivity(activity: Activity){
        activity.finish()
        removeActivity(activity)
    }

    //获取当前栈顶activity
    fun getCurrentActivity() = activities.lastElement()

    fun finishCurrentActivity(){
        finishActivity(activities.lastElement())
    }

    fun finishAllActivity(){
        for (a in activities){
            a.finish()
        }

        activities.clear()
    }

    //app 是否在前台
    fun isForeground() = _count == 1

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        addActivity(activity)
        LogUtils.e(">>>>>>$activity onActivityCreated <<<<<<<")
    }

    override fun onActivityStarted(activity: Activity) {
        LogUtils.e(">>>>>>$activity onActivityStarted <<<<<<<")
        _count++
    }

    override fun onActivityResumed(activity: Activity) {
        LogUtils.e(">>>>>>$activity onActivityResumed <<<<<<<")

    }

    override fun onActivityPaused(activity: Activity) {
        LogUtils.e("======$activity onActivityPaused =======")
    }

    override fun onActivityStopped(activity: Activity) {
        _count--
        LogUtils.e("======$activity onActivityStopped =======")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        removeActivity(activity)
        LogUtils.e("======$activity onActivityDestroyed =======")
    }
}