package cn.wxb.ex

import android.annotation.SuppressLint
import cn.wxb.kt.App
import com.blankj.utilcode.util.GsonUtils
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    launch {
        delay(1000)
        print("world")
    }
    print("hello ")
}

fun main3() {
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

fun ss(){
    val p:Person? = null

    println(p?.name.isNullOrEmpty())
}

class Person{
    val name:String?=null
}

fun mainha() {

    GlobalScope.launch(Dispatchers.IO) {
        println("**** out threadName =  ${Thread.currentThread().name}")
//        val coroutineScope = CoroutineScope(EmptyCoroutineContext)
        GlobalScope.launch {
            println("--->>> in threadName =  ${Thread.currentThread().name}")
//            delay(2000)
//            val c = CoroutineScope(EmptyCoroutineContext)
            GlobalScope.launch(Dispatchers.IO) {
                println("---gjgjjgj>>> in threadName =  ${Thread.currentThread().name}")
                GlobalScope.launch(Dispatchers.IO) {
                    println("--------=-=>>> in threadName =  ${Thread.currentThread().name}")
                }
                delay(2000)
            }
            println("=========")
            delay(1000)
        }
    }

    println(">>>>>>> main blocking <<<<<<<")

    runBlocking {
        while (true) {
        }
    }
}

var job: Job? = null

fun main5() {
    test()
}

var i = 0
fun test() {
    GlobalScope.launch {
        val coroutineScope = CoroutineScope(EmptyCoroutineContext)
        coroutineScope.launch {
            while (true) {
                i++
                Thread.sleep(1000)
                delay(1000)
//            job?.cancel()
                println(">>>>>>>> loop <<<<<<<<<< $i")
            }
        }

        println("===============")
    }
//    job?.start()

    runBlocking {
        delay(5000)
    }
//    job?.cancel()
    println("+++++++++ over ========")

    runBlocking {
        println(">>>>>>> run blocking <<<< ${Thread.currentThread().name}")
        while (true) {

        }
    }
}

fun mainss() {

    val num = 0x2 shl 6
    println(">>>>>>>> $num")
    println("=====1")
    //创建协程方法1：会阻塞线程，通常单元测试时使用
    runBlocking {
        println(">>>>>>> run blocking <<<< ${Thread.currentThread().name}")
    }
    println("------ 2")
    GlobalScope.launch {
        println(">>>>>>>>> sss  ${Thread.currentThread().name}")
    }
    //创建协程方法2：不会阻塞线程，但生命周期与app一致，且不能取消
    //start 默认是DEFAULT-饿汉模式，launch调用后立即进入待调度状态，此时调度器也是默认的，Jvm默认调度器的实现是开启一个线程池
    //start = CoroutineStart.UNDISPATCHED， 立即在当前线程执行协程体
    GlobalScope.launch(context = Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
        println(">>>>>>>>> 3  ${Thread.currentThread().name}")
    }


//    Thread.sleep(100)

    println("====== main 4  ${Thread.currentThread().name}")
    //创建协程方法3：Android推荐用法
    val coroutineScope = CoroutineScope(EmptyCoroutineContext)
    coroutineScope.launch {
        println(">>>>> 5  ${Thread.currentThread().name}")
        getImage()
    }
    //协程最常用的功能是并发，而并发的典型场景就是多线程。可以使用 Dispatchers.IO 参数把任务切到 IO 线程执行
    coroutineScope.launch(Dispatchers.IO) {
        println("--->>> 6  ${Thread.currentThread().name}")
        getTest()
    }
    val s: suspend () -> String = {
        getImage()
        "hello world"
    }
//    s.createCoroutine()
    Thread.sleep(1000)

    val a = 128
    val b: Int? = a
    val c: Int? = a

    println(">>>> ${b == c} >>> --- ${b === c}")


    runBlocking {
        flow {
            for (i in 1..10) {
                delay(10)
                emit(i)
            }
        }.onStart {
            println(">>>> onStart <<<<")
        }
            .onEach {
                println("----- onEach ----- $it")
            }.map {
                it * it
            }
            .onCompletion {
                println("======== finish =========")
            }
//            .count()
//            .launchIn(this)
            .collect {
                println(">>> connlect $it")
            }
    }
    GlobalScope.launch {

    }


    val list = mutableListOf<Int>()
    for (i in 1..10) {
        list.add(i)
    }
    list.forEach {
        if (it and 1 == 0) {
            return@forEach
        }
        if (it > 5) {
            return
        }
        print(">>>>>>>> $it")
    }

    println("--------------")
}

fun main() {

    val array = intArrayOf(2, 4, 8, 7)
    val target = 12
    val ret = twoSum(array, target)
    println(">>>>>> ret = ${ret[0]},${ret[1]}")
}

fun twoSum(numArray: IntArray, target: Int): IntArray {
    val ret = intArrayOf(-1, -1)
    val map = hashMapOf<Int, Int>()
    for((index,value) in numArray.withIndex()){
        val temp = target - value
        if(map.containsKey(temp)){
            ret[0] = map[temp]!!
            ret[1] = index
            break
        }
        map[value] = index
    }

    return ret
}

suspend fun getTest(){
    print(">>>>>>>>>> ${Thread.currentThread().name}")
//    DexClassLoader
//    PathClassLoader
}
suspend fun getImage() = withContext(Dispatchers.IO) {
    delay(2000)
    println("-===--=-=-=7  ${Thread.currentThread().name}")
}


@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")

