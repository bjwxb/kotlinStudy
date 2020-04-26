package cn.wxb.kt.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * description:
 *
 * author: wuxiaobo
 * date: 2020/4/26 23:36
 */
/**
 * 统一线程处理
 *
 * @param <T>
 * @return
</T> */
fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> =
    FlowableTransformer { observable ->
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    } // compose简化线程 统一处理线程

/**
 * 生成Flowable
 *
 * @param <T>
 * @return
</T> */
fun <T> createData(t: T): Flowable<T> {
    return Flowable.create({ emitter ->
        try {
            emitter.onNext(t)
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }, BackpressureStrategy.BUFFER)
}

/**
 * 生成Flowable
 *
 * @param <T>
 * @return
</T> */
fun <T> createData(t: List<T>): Flowable<List<T>> {
    return Flowable.create({ emitter ->
        try {
            emitter.onNext(t)
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }, BackpressureStrategy.BUFFER)
}
