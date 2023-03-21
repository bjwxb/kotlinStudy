package cn.wxb.kt.ui.finger.bean

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/7/5 17:22
 */
class VerificationDialogStyleBean {
    /**
     * android 6.0
     */
    var fingerprintColor:Int? = null
    var cancelTextColor:Int? = null
    var usePwdTextColor:Int? = null
    var usePwdVisible:Boolean = false

    /**
     * android 9.0
     *
     */
    private val title: String? = null //标题
    private val subTitle: String? = null //副标题
    private val description: String? = null //说明
    private val cancelBtnText: String? = null //取消按钮文案
}