package cn.wxb.kt.mvvm.base

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import cn.wxb.kt.mvvm.event.Message
import cn.wxb.kt.mvvm.event.SingleLiveEvent
import cn.wxb.kt.mvvm.network.ExceptionHandle
import cn.wxb.kt.mvvm.network.ResponseThrowable
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 14:43
 */
open class BaseViewModel : AndroidViewModel(Utils.getApp()), LifecycleObserver{
    val defUI: UIChange by lazy { UIChange() }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 用流的方式进行网络请求
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    /**
     *  不过滤请求结果
     * @param block 请求体
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun launchGo(
            block: suspend CoroutineScope.() -> Unit,
            error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
                defUI.toastEvent.postValue(it.errMsg)
            },
            complete: suspend CoroutineScope.() -> Unit = {},
            isShowDialog: Boolean = true
    ) {
        if (isShowDialog) {
            defUI.showDialog.call()
        }
        launchUI {
            handleException(
                    withContext(Dispatchers.IO) {
                        block
                    },
                    { error(it) },
                    {
                        if (isShowDialog){
                            defUI.dismissDialog.call()
                            LogUtils.e("111111111111111111111")
                        }
                        complete()
                    }
            )
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun <T> launchOnlyresult(
            block: suspend CoroutineScope.() -> IBaseResponse<T>,
            success: (T) -> Unit,
            error: (ResponseThrowable) -> Unit = {
                defUI.toastEvent.postValue(it.errMsg)
            },
            complete: () -> Unit = {},
            isShowDialog: Boolean = true
    ) {
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                    { withContext(Dispatchers.IO) { block() } },
                    { res ->
                        executeResponse(res) { success(it) }
                    },
                    {
                        error(it)
                    },
                    {
                        if (isShowDialog){
                            defUI.dismissDialog.call()
                            LogUtils.e("dddddddddddddddddddddddd")
                        }

                        complete()
                    }
            )
        }
    }

    /**
     * 请求结果过滤
     */
    private suspend fun <T> executeResponse(
            response: IBaseResponse<T>,
            success: suspend CoroutineScope.(T) -> Unit
    ) {
        coroutineScope {
            if (response.isSuccess()) success(response.data())
            else throw ResponseThrowable(response.message())
        }
    }

    /**
     * 异常统一处理
     */
    private suspend fun <T> handleException(
            block: suspend CoroutineScope.() -> IBaseResponse<T>,
            success: suspend CoroutineScope.(IBaseResponse<T>) -> Unit,
            error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
            complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Throwable) {
                e.printStackTrace()
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }


    /**
     * 异常统一处理
     */
    private suspend fun handleException(
            block: suspend CoroutineScope.() -> Unit,
            error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
            complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                e.printStackTrace()
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }


    /**
     * UI事件
     */
    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
        val toastEvent by lazy { SingleLiveEvent<String>() }
        val msgEvent by lazy { SingleLiveEvent<Message>() }
    }
}