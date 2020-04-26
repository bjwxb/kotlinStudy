package cn.wxb.kt.rx

import cn.wxb.kt.util.rxSchedulerHelper
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.processors.FlowableProcessor
import io.reactivex.rxjava3.processors.PublishProcessor

/**
 * description:
 *
 * author: wuxiaobo
 * date: 2020/4/26 23:27
 */
object RxBus{
    //PublishSubject 只会把在订阅发生的时间点之后来自原始Flowable的数据发射给观察者
    val bus: FlowableProcessor<Any> = PublishProcessor.create<Any>().toSerialized()

    //提供了一个新的事件 发射数据
    fun post(o: Any) = bus.onNext(o)

    //根据传递的eventType类型返回特定类型（event Type）的被观察者
    fun <T> toFlowable(eventType: Class<T>): Flowable<T> = bus.ofType(eventType)

    //封装默认订阅
    fun <T> toDefaultFlowable(eventType: Class<T>, act: Consumer<T>): Disposable = bus.ofType(eventType).compose(rxSchedulerHelper<T>()).subscribe(act)
}