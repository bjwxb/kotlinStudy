package cn.wxb.kt.study

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/14 15:56
 */
fun main(){
    val time = 301
    println(Math.ceil((time/60.0)).toInt())

//    val p1 = Person().apply { this.name = "July" }
//    val p2 = p1.copy()
//    println(">>>>>> ${p1 == p2}")
//    p2.name = "Jack"
//    println("======== ${p1.name}  ---- ${p2.name}  》》${p1 == p2}")

}


class Person:Cloneable {
    var name: String = ""
    var age: Int = 19
}

public inline fun <T> run2(block:()->T) : T{

    return block()
}