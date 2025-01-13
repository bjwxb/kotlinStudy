package cn.wxb.ex

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * AUTHOR: wuxiaobo
 * DATE:  2024/12/27
 * INTRODUCE: flow channelFlow， CallbackFlow
 * 如果flow满足要求，就使用flow
 * 如果需要在flow里面使用缓冲和异步，使用ChannelFlow
 * 如果需要在flow外部发送数据，使用带有awaitClose的CallbackFlow
 */
fun main1() = runBlocking {
    val s = System.currentTimeMillis()
    /**
     * 从结果可以看出 每个emit都必须在下一个emit调用之前被消耗掉。
     * 这是因为没有缓冲区来存储额外的emit，因此每个emit都必须排队。
     *
     * 没有任何通道的情况下，emit（发送）和consume（消耗）是同步进行的。
     * 这在我们想确保在前一个事件被消耗之前不emit的情况下是好的。但是，它会减慢emit（发送）过程。
     *
     * >>>>> emitting 1
     * >>>>> consuming 1
     * >>>>> emitting 2
     * >>>>> consuming 2
     * >>>>> emitting 3
     * >>>>> consuming 3
     * >>>>> emitting 4
     * >>>>> consuming 4
     * >>>>> emitting 5
     * >>>>> consuming 5
     */
    flow {
        for (i in 1..5) {
            println(">>>>> emitting $i")
            emit(i)
        }
    }.collect {
        delay(100)
        println(">>>>> consuming $it")
    }

    val diff = System.currentTimeMillis() - s
    println(">> diff = $diff <<")
}

/**
 * 为了使emit（发送）速度更快，我们可以给它增加一个缓冲区（使用buffer方法）
 */
fun main22() = runBlocking {
    val s = System.currentTimeMillis()
    flow {
        for (i in 1..5) {
            println("emit $i")
            emit(i)
        }
    }
        .buffer(5)
        .collect {
            delay(100)
            println("consuming $it")
        }

    val diff = System.currentTimeMillis() - s
    println(">> diff = $diff <<")
}

/**
 * 结果类似flow使用了buffer(3), 但是channelFlow有更多的缓存区，默认是64个缓冲区
 * 同样可以使用buffer方法来设置缓冲区大小
 * >> emit 1
 * >> emit 2
 * >> emit 3
 * >> emit 4
 * >> emit 5
 * >>> consuming 1
 * >>> consuming 2
 * >>> consuming 3
 * >>> consuming 4
 * >>> consuming 5
 *
 */
fun main33() = runBlocking {
    channelFlow {
        for (i in 1..5) {
            println(">> emit $i")
            send(i)
        }
    }.collect {
        delay(100)
        println(">>> consuming $it")
    }
}

fun <T> Flow<T>.flowMerge(other: Flow<T>): Flow<T> = flow {
    collect { emit(it) }
    other.collect { emit(it) }
}

// channelFlow加快了执行流程，并让合并工作交替执行
fun <T> Flow<T>.channelMerge(other: Flow<T>): Flow<T> = channelFlow {
    // launch 使得collect不再阻塞，可以与other.collect并行执行，
    launch {
        collect { send(it) }
    }
    other.collect { send(it) }
}

fun main55() = runBlocking {
    val abcFlow = flow {
        ('A'..'E').forEach {
            delay(50)
            emit(it)
        }
    }

    val numFlow = flow {
        (1..5).forEach {
            delay(50)
            emit(it)
        }
    }

    //flowMerge 按顺序merge
    //channelMerge //可以并行交叉执行
    numFlow.channelMerge(abcFlow).collect {
        delay(50)
        println(">> $it <<")
    }
}

/**
 * channelFlow 可以使用
 * @return Unit
 */
fun main(): Unit = runBlocking {
    var trySendData: (data:Int) -> Unit = {}
    var sendData: suspend (data: Int) -> Unit = {}
    var closeChannel: () -> Unit = {}

    launch {
        // send 是suspend方法
        // 如果在ChannelFlow中，限制使用非suspend方法时，可以使用trySend
        callbackFlow {
            for (i in 1..5) {
                send(i)
            }
            sendData = {
                send(it * it)
            }
            closeChannel = {
                close()
            }

            awaitClose {
                sendData = {}
                closeChannel = {}
            }
        }.collect {
            println(it)
        }
    }
    delay(10)
    println("sending 6")
    sendData(6)
    sendData(7)
    sendData(8)
    closeChannel()
}

