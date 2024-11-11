package cn.wxb.ex

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates

/**
 * AUTHOR: wuxiaobo
 * DATE: 2024/6/25
 * INTRODUCE: info
 */
class Ex4 {

}

interface Base {
    fun printHah()
}

class BaseImpl : Base{

    /**
     * 使用 Delegates.observable() 可以实现可观察属性，函数接受两个参数：第一个参数为初始值，第二个参数为属性值变化的回调。
     * 函数的返回值是 ObservableProperty 可观察属性，它在调用 setValue(...) 是触发回调
     */
    var name by Delegates.observable("init"){prop, old, new ->
        println("$prop>>> old = $old, new = $new")
    }
    override fun printHah() {
        println(">>>> hah <<<<")
    }

}

class Div(b:Base):Base by b


fun main() {
//    val num = 14000
//    val a = (num /10000f).toDouble()
//    println(">> a = $a")
//
//    val b:Double = num / 10000.0
//    println(">>> b = $b")
//    val c = BaseImpl()
//    Div(c).printHah()
//
//    c.name = "July"
//    c.name = "Jack"

    val list = mutableListOf(1, 2, 3)

    val local = mutableListOf(4, 5)
    list.addAll(0, local)
    println(">>> $list")
    GlobalScope.launch(Dispatchers.Main) {
        println(">>>>>>>> ${Thread.currentThread().name}")
        val a = async {
            println("====== ${Thread.currentThread().name}")
        }
        a.await()

        println("========hah")
    }


    runBlocking {
        delay(200)
    }
}