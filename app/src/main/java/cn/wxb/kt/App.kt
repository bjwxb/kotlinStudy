package cn.wxb.kt

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import cn.wxb.kt.widget.AppObserver
import cn.wxb.manager.ActivityManager

/**
 * description:
 *
 * author: wuxiaobo
 * date: 2020/4/25 22:43
 */
class App : Application() {
    private var allActivities : HashSet<Activity>? = null;

    /**
     * object 关键字可以表达两种含义：一种是对象表达式,另一种是 对象声明。
     * 1.继承一个匿名对象
     * 2.用object 修饰的类为静态类，里面的方法和变量都为静态的。
     *
     * companion object {}中用来修饰 静态常量，或者静态方法，单例等等
     */
    companion object {
        //lateinit---它告诉编译器，这个变量会被初始化，并且不会为null，但是在声明这里，我暂时还不知道什么时候会被初始化。
        private lateinit var app:Application
        fun getInstance() = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this;
        //ProcessLifecycleOwner 可以实现监听app前后台切换同等效果
        //ProcessLifecycleOwner.get().lifecycle.addObserver(CustomLifecycleObserver());
        registerActivityLifecycleCallbacks(ActivityManager.getInstance())
//        RetrofitUtils.init()

        ProcessLifecycleOwner.get().lifecycle.addObserver(AppObserver());

    }
}