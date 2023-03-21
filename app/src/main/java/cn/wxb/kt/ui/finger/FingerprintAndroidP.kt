package cn.wxb.kt.ui.finger

import android.app.Activity
import android.content.Context
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import cn.wxb.kt.ui.finger.bean.VerificationDialogStyleBean

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/7/6 18:39
 */
@RequiresApi(api = Build.VERSION_CODES.P)
class FingerprintAndroidP : IFingerprint{

    private val fingerprintCallback:FingerprintCallback? = null
    private val cancellationSignal:CancellationSignal? = null
    private val cryptoObject:BiometricPrompt.CryptoObject? = null

    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder{
        val instance = FingerprintAndroidP()
    }




    override fun canAuthenticate(context: Context?, callback: FingerprintCallback?): Boolean {
        if(context == null){
            return false
        }

        /*
         * 硬件是否支持指纹识别
         */
        if (!FingerprintManagerCompat.from(context).isHardwareDetected) {
            fingerprintCallback!!.onHwUnavailable()
            return false
        }

        //是否已添加指纹
        if (!FingerprintManagerCompat.from(context).hasEnrolledFingerprints()) {
            fingerprintCallback!!.onNoneEnrolled()
            return false
        }

        return true
    }

    override fun authenticate(
        context: Activity?,
        verificationDialogStyleBean: VerificationDialogStyleBean?,
        callback: FingerprintCallback?
    ) {
        TODO("Not yet implemented")
    }
}