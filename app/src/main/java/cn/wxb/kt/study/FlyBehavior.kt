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