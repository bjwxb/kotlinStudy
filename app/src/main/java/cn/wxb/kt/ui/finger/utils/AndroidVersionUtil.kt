package cn.wxb.kt.ui.finger.utils

import android.os.Build

/**
 * @desc: android 版本判断
 * @author: wuxiaobo
 * @date: 2022/7/6 14:47
 */
object AndroidVersionUtil {

    /**
     * 高于Android P（9.0）
     *
     * @return
     */
    fun isAboveAndroidP(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    /**
     * 高于Android M（6.0）
     *
     * @return
     */
    fun isAboveAndroidM(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }
}