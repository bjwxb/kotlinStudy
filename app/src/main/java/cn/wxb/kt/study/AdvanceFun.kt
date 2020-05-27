package cn.wxb.kt.study

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/14 15:56
 */
fun main(){
    val str = "hello"
    run{
        val str = "java"
        println(str)
    }
    println(str)
}

public inline fun <T> run2(block:()->T) : T{

    return block()
}