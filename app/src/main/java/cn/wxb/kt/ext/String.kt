package cn.wxb.kt.ext

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2021/10/12 6:29 下午
 */
fun String.hello():String{
    return "Hello $this World!"
}


fun main() {
    print("java".hello());
}