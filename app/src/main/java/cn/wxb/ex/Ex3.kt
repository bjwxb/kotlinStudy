package cn.wxb.ex

/**
 * @author: wuxiaobo
 * @date: 2023/11/1 15:30
 */
class Ex3 {

}

fun main() {
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