package cn.wxb.ex

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.NullPointerException

/**
 * @desc: 协程
 * @author: wuxiaobo
 * @date: 2023/2/2 11:14
 */
class Ex2 {

}

fun main() {
//    testCoroutineContext()
//    testCoroutineStart()
//    testFlow()
//    testFlowOf()
    testPlus()
}

fun testPlus(){
    val list = mutableListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7)
    var i = 1
//    val a = i++ // a = 1, i=2
//    i = i++ //i = 1
//    val j = i+(2 * i++)

    i = i++ * ++i
    println(i)
    kotlin.runCatching {
        println(">>>>>>> 1")
        throw NullPointerException()

    }.onSuccess {
        println(">>>>>>> onsuccess")
    }.onFailure {
        println(">>>>>>> failed")
    }.getOrElse {
        println("-=-=-=-=")
    }


    //i++：先自增，再返回自增之前的值
    //++i：先自增，再返回自增之后的值
    //不论是前++还是后++，它们的共同点就是先自增
    var m = 1
    var n = 1
    val ret =m++ + ++m + ++n + n++ //8
    println(ret)

//    val arr = Array(10){0}
//    var i = 1
//    arr[i++] = i //arr[0] = 2, i=2
//    arr[++i] = i //arr[3] = 3
//
//    arr[i] = i++ //arr[3] = 3
//    arr[i] = ++i //arr[4] = 5
//    arr.forEach {
//        println(it)
//    }
}

fun testFlowOf() = runBlocking() {
    flowOf(1, 2, 3)
        .onEach {
            println("******** ${it*it}")
            delay(1000)
        }
        .map {
            it + it
        }
        .onCompletion {
            println("---- onComplete ----")
        }
        .onStart {
            println(">>> start <<<")
        }
        .onEmpty {
            println("-=-= empty -=-=")
        }
        .collect {
            println(">>>> $it")
        }
//        .launchIn(this)

}

fun testFlow(){
    runBlocking {
        val flow = simpleFlow()
//            .flowOn(Dispatchers.IO) //改变发射流的线程（也叫上下文）
        flow.collect {
            println(">> thread ${Thread.currentThread().name}")
            println("collect data $it")
        }

        flow.take(3)
            .filter { it and 1 == 0 }
            .map {
                "map ret $it"
            }
            .collect {
                println("collect again, data $it")
            }
    }

}

//流的收集总是在调用协程的上下文中发生，流的该属性称为上下文保存
//flow{...}构建器中的代码必须遵循上下文保存属性，并且不允许从其他上下文中发射(emit)
fun simpleFlow() = flow {
    println("simpleFlow thread ${Thread.currentThread().name}")
//    withContext(Dispatchers.IO){

        for(i in 1..5){
            emit(i)
        }
//    }
}


fun testCoroutineContext(){
    val coroutine1 = Job() + CoroutineName("这是第一个协程")
    println("coroutine1>> $coroutine1")
    val coroutine2 = coroutine1 + Dispatchers.Default + CoroutineName("这是第二个协程")
    println("coroutine2>> $coroutine2")
    val coroutine3 = coroutine2 + Dispatchers.IO + CoroutineName("这是第三个协程")
    println("coroutine2>> $coroutine3")
}

private fun testCoroutineStart(){
    val defaultJob = GlobalScope.launch{
        println("defaultJob, CoroutineStart.DEFAULT")
    }
    defaultJob.cancel()
    val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY){
        println("lazyJob, CoroutineStart.LAZY")
    }
    lazyJob.cancel()
    val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC){
        println("atomicJob, CoroutineStart.ATOMIC挂起前")
        delay(100)
        println("atomicJob, CoroutineStart.ATOMIC挂起后")
    }
    atomicJob.cancel()
    val undispatchedJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED){
        println("undispatchedJob, CoroutineStart.UNDISPATCHED挂起前")
        delay(100)
        println("undispatchedJob, CoroutineStart.UNDISPATCHED挂起后")
    }
    undispatchedJob.cancel()
}
