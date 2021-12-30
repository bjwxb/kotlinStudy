package cn.wxb.ex

import android.annotation.SuppressLint
import cn.wxb.kt.App
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2021/12/21 10:43 上午
 */
class Example {


}

fun main2() = runBlocking {
   launch{
        delay(1000)
        print("world")
    }
    print("hello ")
}

fun main3(){
//    repeat(100_000){
//        GlobalScope.launch {
//            delay(100)
//            println('.')
//        }
//    }

    val coroutineScope = CoroutineScope(EmptyCoroutineContext)
    val job = coroutineScope.launch(Dispatchers.IO) {
        delay(1000)
        print("world")
    }
    print("hello ")
   runBlocking {
       job.join()
   }
}

fun main() {
    println("=====1")
    //创建协程方法1：会阻塞线程，通常单元测试时使用
    runBlocking {
        println(">>>>>>> run blocking <<<< ${Thread.currentThread().name}")
    }
    println("------ 2")
    //创建协程方法2：不会阻塞线程，但生命周期与app一致，且不能取消
    GlobalScope.launch(Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
        println(">>>>>>>>> 3  ${Thread.currentThread().name}")
    }
//    Thread.sleep(100)

    println("====== main 4  ${Thread.currentThread().name}")
    //创建协程方法3：Android推荐用法
    val coroutineScope = CoroutineScope(EmptyCoroutineContext)
    coroutineScope.launch{
        println(">>>>> 5  ${Thread.currentThread().name}")
        getImage()
    }
    //协程最常用的功能是并发，而并发的典型场景就是多线程。可以使用 Dispatchers.IO 参数把任务切到 IO 线程执行
    coroutineScope.launch(Dispatchers.IO) {
        println("--->>> 6  ${Thread.currentThread().name}")

    }
    val s:suspend () -> String = {
        getImage()
        "hello world"
    }
//    s.createCoroutine()
    Thread.sleep(3000)
}

suspend fun getImage() = withContext(Dispatchers.IO){
    delay(2000)
    println("-===--=-=-=7  ${Thread.currentThread().name}")
}


@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")

