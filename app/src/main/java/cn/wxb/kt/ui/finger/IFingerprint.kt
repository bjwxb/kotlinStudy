package cn.wxb.kt.ui.finger

import android.app.Activity
import android.content.Context
import cn.wxb.kt.ui.finger.bean.VerificationDialogStyleBean

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/7/6 15:02
 */
interface IFingerprint {

    /**
     * 检测指纹硬件是否可用，及是否添加指纹
     * @param context
     * @param callback
     * @return
     */
    fun canAuthenticate(context: Context?, callback: FingerprintCallback?): Boolean

    /**
     * 初始化并调起指纹验证
     *
     * @param context
     * @param verificationDialogStyleBean
     * @param callback
     */
    fun authenticate(
        context: Activity?,
        verificationDialogStyleBean: VerificationDialogStyleBean?,
        callback: FingerprintCallback?
    )
}