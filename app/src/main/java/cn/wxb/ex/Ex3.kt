package cn.wxb.ex

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * @author: wuxiaobo
 * @date: 2023/11/1 15:30
 */
class Ex3 {

}

fun main(){

//    runBlocking {
//        val a = test1()
//        println(">>>> result = $a")
//    }

    val a = -123
    val b = 10

    println(">>>> ${a/b} + ${a%b}")

//    val s = "ss"
//    kotlin.runCatching {
//        val a = s as? Int
//        print(a) // a = null
//    }.getOrElse {
//        it.printStackTrace()
//    }
}

interface CallbackHah{
    fun action()
}

private var callbackHah:CallbackHah? = null

fun setCallback(c: CallbackHah){
    callbackHah = c
}
suspend fun test1():Int = suspendCancellableCoroutine {
    println("----------1")
    setCallback(object :CallbackHah{
        override fun action() {
            if(it.isActive){
                println("----------2")
                it.resume(1)
                println("----------3")
            }
        }
    })
    println("----------4")
    MainScope().launch {
        println("----------5")
        write()
        println("----------6")
    }
    println("----------7")
}
suspend fun write(){
    println(">>>>>> write <<<<<<<<")
    delay(1000)
    callbackHah?.action()
    println(">>>>>> write over <<<<<")
}


fun mains() {

    val a = 1
    val b = a xor 1
    val c = b xor 1
    val d = c xor 1
    val e = d xor 1
    println(">>>> ${b}, $c, $d, $e")
    val s1 = Student()
    println(">> ${s1.name}, ${s1.age}")
    change(s1)
    println(">> ${s1.name}, ${s1.age}")
    change2(s1)
    println(">> ${s1.name}, ${s1.age}")

}
fun change(s:Student){
    s.name = "Jack"
    s.age = 30
}

fun change2(s:Student){
    var tmp = s
    val ss = Student().apply {
        this.name = "hah"
        this.age = 31
    }
    tmp = ss
}

class Student{
    var name = "July"
    var age = 22
}