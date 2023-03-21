package cn.wxb.kt.mvvm.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.wxb.kt.R
import cn.wxb.kt.mvvm.event.Message
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import java.lang.reflect.ParameterizedType

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 14:54
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: VM

    protected var mBinding: DB? = null

    //是否第一次加载
    private var isFirst: Boolean = true

    private var dialog: MaterialDialog? = null

    lateinit var mContext:Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val cls = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
        if (ViewDataBinding::class.java != cls && ViewDataBinding::class.java.isAssignableFrom(cls)) {
            mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
            mBinding?.lifecycleOwner = this
            return mBinding?.root
        }
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onVisible()
        createViewModel()
        lifecycle.addObserver(viewModel)
        //注册 UI事件
        registorDefUIChange()
        initView(savedInstanceState)
    }

    open fun initView(savedInstanceState: Bundle?) {}

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    abstract fun layoutId(): Int

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && !isFirst){
            show()
        }

        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
        }

    }

    //fragment通过add，show，hide方式时，此回调生效
    override fun onHiddenChanged(hidden: Boolean) {
        if(!hidden){
            show()
        }
    }

    /**
     * 懒加载
     */
    open fun lazyLoadData() {}

    //fragment显示在前台
    open fun show(){

    }

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        viewModel.defUI.showDialog.observe(viewLifecycleOwner, Observer {
            LogUtils.e("qqqqqqqqqqqqqqqqqqqqqqqqq")
            showLoading()
        })
        viewModel.defUI.dismissDialog.observe(viewLifecycleOwner, Observer {
            LogUtils.e("wwwwwwwwwwwwwwwwwwwww")
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(viewLifecycleOwner, Observer {
            ToastUtils.showShort(it)
        })
        viewModel.defUI.msgEvent.observe(viewLifecycleOwner, Observer {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: Message) {}

    /**
     * 打开等待框
     */
    fun showLoading() {
        if (dialog == null) {
            dialog = context?.let {
                MaterialDialog(it)
                        .cancelable(true)
                        .cornerRadius(5f)
                        .customView(R.layout.loading_dialog)
                        .lifecycleOwner(this)
                        .maxWidth(R.dimen.dp_120)
            }
        }
        dialog?.show()
    }

    /**
     * 关闭等待框
     */
    fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

    /**
     * 是否和 Activity 共享 ViewModel,默认不共享
     * Fragment 要和宿主 Activity 的泛型是同一个 ViewModel
     */
    open fun isShareVM(): Boolean = false

    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
            val viewModelStore = if (isShareVM()) activity!!.viewModelStore else this.viewModelStore
            viewModel = ViewModelProvider(viewModelStore, ViewModelFactory()).get(tClass) as VM
        }
    }

}