package cn.wxb.kt.ui.home.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wxb.ex.log
import cn.wxb.kt.R
import cn.wxb.kt.databinding.FragmentMedicBinding
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import cn.wxb.kt.ui.home.viewmodel.Student
import cn.wxb.kt.util.download.*
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.RomUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.fragment_medic.*


/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/5 11:02
 */
class MedicFragment : BaseFragment<MainViewModel, FragmentMedicBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun isShareVM(): Boolean {
        return true;
    }

    override fun layoutId() = R.layout.fragment_medic


    @SuppressLint("CheckResult")
    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.vm = viewModel
        initRv()

        tvContent.setOnClickListener(View.OnClickListener {
            viewModel.name.value = "medic modify value"
            viewModel.student.value = Student().apply {
                this.name = "Jack"
            }
            LogUtils.e(">>>>>> ${RomUtils.isHuawei()} ?>>>>${RomUtils.getRomInfo().name == "honor"}")
            testFinger()

        })
        tvVideo.setOnClickListener(View.OnClickListener {
            RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {
                    if(it){
                        showLoading()
                        val url = "https://slzx-sit-pubilc.oss-cn-hangzhou.aliyuncs.com/mall_B/%E6%B5%8B%E8%AF%95%E8%A7%86%E9%A2%91/2022032913400575485201.mp4"
                        DownloadUtil.getInstance().downloadFile(url){
                            dismissLoading()
                        }
                    }
                }
        })

        testWebView()
   }

    private fun testWebView(){
        mBinding?.dWebView?.run {
//            this.addJavascriptObject(JsApi(), "Controller")
            this.addJavascriptObject(JsApi(), "Storage")
            val url = "https://scenario-skills-h5-test.beantechyun.cn/#/?userVin=LGW52181199495766&sl=shalong"
            loadUrl(url)
        }


    }

    private fun testFinger(){
        val manager = BiometricManager.from(requireContext())

        when (isFingerprintAvailable()) {
            BiometricManager.BIOMETRIC_SUCCESS ->//可以进行验证
            {
                startAuthenticate()
                LogUtils.e("--------")
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->//没有合适的传感器或者没设置密码，例如手机没有指纹传感器
                LogUtils.e( "No biometric features available on this device.")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->//传感器当前不可用，稍后再试
                LogUtils.e( "Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {//信息没有录入，比如还没录入指纹
                LogUtils.e(">>>>>>>>>>Prompts the user to create credentials that your app accepts. ")
                // Prompts the user to create credentials that your app accepts.
//                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
//                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
//                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
//                }
//                startActivityForResult(enrollIntent, 100)
            }
        }

    }

    private fun startAuthenticate(){
        authenticate(requireActivity(), callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                log("'>>>>>>>>> $errorCode")
                BiometricPrompt.ERROR_USER_CANCELED//取消了指纹识别
                BiometricPrompt.ERROR_LOCKOUT//失败5次，已锁定，请30秒后在试
                BiometricPrompt.ERROR_LOCKOUT_PERMANENT//失败次数太多，指纹验证已锁定，请改用密码，图案等方式解锁
                BiometricPrompt.ERROR_NEGATIVE_BUTTON
                BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL // 尚未设置密码，图案等解锁方
                BiometricPrompt.ERROR_NO_SPACE//可用空间不足
                BiometricPrompt.ERROR_TIMEOUT//验证超时
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                log(">>>> failed")
            }
        })
    }

    /**
     * return 是否可用的状态码
     * BiometricManager.BIOMETRIC_SUCCESS ->//可以进行验证
     * BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->//硬件不支持
     * BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->//传感器当前不可用，稍后再试
     * BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED //信息没有录入，比如还没录入指纹
     */
    fun isFingerprintAvailable(): Int {
        val manager = BiometricManager.from(requireContext())
        return manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
    }


    /**
     * 开始验证
     * @param activity
     * @param callBack 验证结果回调
     */
    private fun authenticate(activity:FragmentActivity, callback:BiometricPrompt.AuthenticationCallback) {
        val promptInfo: PromptInfo = createUi()
        val prompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity), callback)
        prompt.authenticate(promptInfo)
        DeviceUtils.isEmulator()
    }

    private fun createUi():PromptInfo {
        DeviceUtils.isEmulator()
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("请验证指纹")
            .setNegativeButtonText("取消")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .build()
    }


    private fun initRv(){
        rvList.adapter
        rvList.layoutManager = LinearLayoutManager(mContext)
    }

//    fun init(view:View){
////       val vm = ViewModelProviders.of(activity!!).get(AppRemindViewModel::class.java)
////        val vm = ViewModelProvider(activity!!)[AppRemindViewModel::class.java]
//        val vm = ViewModelProvider(activity!!)[MainViewModel::class.java]
//    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MedicFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}

