package cn.wxb.kt.ui.finger

import android.R
import android.app.Activity
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.core.os.CancellationSignal
import androidx.fragment.app.DialogFragment
import com.blankj.utilcode.util.SnackbarUtils.dismiss
import javax.crypto.Cipher


/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/7/6 20:10
 */
@RequiresApi(Build.VERSION_CODES.M)
class FingerprintDialogFragment : DialogFragment() {
//    private var fingerprintManager: FingerprintManager? = null
//    private var mCancellationSignal: CancellationSignal? = null
//    private var mCipher: Cipher? = null
//    private var mActivity: Activity? = null
//    private var errorMsg: TextView? = null
//    private var mOnFingerResultListener: OnFingerResultListener? = null
//    private var isSelfCancelled // 标识是否是用户主动取消的认证。
//            = false
//
//    fun setCipher(cipher: Cipher?) {
//        mCipher = cipher
//    }
//
//    fun setOnFingerResultListener(onFingerResultListener: OnFingerResultListener?) {
//        mOnFingerResultListener = onFingerResultListener
//    }
//
//    fun onAttach(context: Context?) {
//        super.onAttach(context)
//        mActivity = getActivity()
//    }
//
//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        fingerprintManager = getContext().getSystemService(FingerprintManager::class.java)
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Material_Light_Dialog)
//    }
//
//    @Nullable
//    fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, savedInstanceState: Bundle?): View {
//        val v: View = inflater.inflate(R.layout.dialog_fingerprint, container, false)
//        errorMsg = v.findViewById(R.id.error_msg)
//        val cancel: TextView = v.findViewById(R.id.cancel)
//        cancel.setOnClickListener(object : View.OnClickListener() {
//            fun onClick(v: View?) {
//                dismiss()
//                stopListening()
//            }
//        })
//        return v
//    }
//
//    fun onResume() {
//        super.onResume()
//        // 开始指纹认证监听
//        startListening(mCipher)
//    }
//
//    fun onPause() {
//        super.onPause()
//        // 停止指纹认证监听
//        stopListening()
//    }
//
//    private fun startListening(cipher: Cipher?) {
//        isSelfCancelled = false
//        mCancellationSignal = CancellationSignal()
//        fingerprintManager!!.authenticate(
//            FingerprintManager.CryptoObject(cipher),
//            mCancellationSignal,
//            0,
//            object : FingerprintManager.AuthenticationCallback() {
//                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                    if (!isSelfCancelled) {
//                        errorMsg!!.text = errString
//                        if (errorCode == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
//                            //Toast.makeText(mActivity, errString, Toast.LENGTH_SHORT).show();
////                            ToastUtil.showToast(mActivity, errString.toString() + "")
//                            if (mOnFingerResultListener != null) {
//                                mOnFingerResultListener!!.fingerResult(false)
//                            }
//                            dismiss()
//                        }
//                    }
//                }
//
//                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence) {
//                    errorMsg!!.text = helpString
//                }
//
//                override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
//                    // ToastUtil.showToast(mActivity, "指纹认证成功");
//                    if (mOnFingerResultListener != null) {
//                        mOnFingerResultListener!!.fingerResult(true)
//                    }
//                    dismiss()
//                    //mActivity.onAuthenticated();
//                }
//
//                override fun onAuthenticationFailed() {
//                    errorMsg!!.text = "指纹认证失败，请再试一次"
//                    if (mOnFingerResultListener != null) {
//                        mOnFingerResultListener!!.fingerResult(false)
//                    }
//                }
//            },
//            null
//        )
//    }
//
//    private fun stopListening() {
//        if (mCancellationSignal != null) {
//            mCancellationSignal.cancel()
//            mCancellationSignal = null
//            isSelfCancelled = true
//        }
//    }
//
//    interface OnFingerResultListener {
//        fun fingerResult(result: Boolean?)
//    }
}