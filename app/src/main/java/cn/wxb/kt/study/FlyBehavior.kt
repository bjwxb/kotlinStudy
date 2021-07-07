package cn.wxb.kt.study

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/19 13:40
 */
interface FlyBehavior {
    var speed:Int//属性，抽象的

    fun fly()//抽象方法

    fun run(){//可选方法体
        println(">>>>>>>>>>> run ")
    }
}

class Bird : FlyBehavior{
    override var speed = 10//重写属性

    override fun fly() {//重写方法
    }
}

fun main() {
    tableSizeFor(17)
}

//找到大于等于cap的最小的2的幂
fun tableSizeFor(cap:Int) {
    var i = cap - 1;

    i = i or (i ushr 1)
    i = i or (i ushr 2)
    i = i or (i ushr 4)
    i = i or (i ushr 8)
    i = i or (i ushr 16)

    print("---------- ${i+1}")
}