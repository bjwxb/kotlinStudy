package cn.wxb.ex

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy


/**
 * AUTHOR: wuxiaobo
 * DATE:  2024/9/20
 * INTRODUCE: info
 */
class Ex5 {
}

interface UserInterface{
    fun login(account:String, pwd:String)
}

fun main(){
//    val userInterface = Proxy.newProxyInstance(UserInterface::class.java.classLoader,
//        arrayOf<Class<*>>(UserInterface::class.java),
//        InvocationHandler { proxy, method, args ->
//            println("method = ${method.name}   args = ${args.contentToString()}")
//        }
//    ) as UserInterface
//
//    userInterface.login("july", "pwd")
//    println(userInterface)

    val s = "heel"
    println(">>> ${s.contains("")}")
}