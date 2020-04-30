package cn.wxb.kt.mvvm.utils

import cn.wxb.kt.mvvm.base.IBaseResponse
import cn.wxb.kt.mvvm.network.ResponseThrowable
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 11:50
 */
@ExperimentalCoroutinesApi
fun <T> Flow<IBaseResponse<T>>.applyTransform(): Flow<T> {
    return this
            .flowOn(Dispatchers.IO)
            .map {
                if (it.isSuccess()) return@map it.data()
                else throw ResponseThrowable(it.message())
            }
}