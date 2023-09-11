package cn.wxb.leetcode

/**
 * @desc:
 * 66. 加一
 * 69. x 的平方根
 * @author: wuxiaobo
 * @date: 2023/3/23 17:19
 */
class day7 {
}

fun main() {
    test66()
//    test69()
}

fun test66(){
    val nums = intArrayOf(4,1,2,1)
    val ret = plusOne(nums)
    println(ret.toList())
}

fun test69(){
    print(mySqrt(8))
}

/** 66 start **/
/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 输入：digits = [4,3,2,1] 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321
 * @param digits IntArray
 * @return IntArray
 */
fun plusOne(digits: IntArray): IntArray {
    val len = digits.size
    val ret = intArrayOf()
    var i = len
    while(i < len){
        if(digits[i] == 9){

        } else {
            digits[i] = digits[i]+1
        }
        i--
    }
    return ret
}
/** 66 end **/

/** 69 start **/
/**
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 输入：x = 4  输出：2
 * 输入：x = 8   输出：2  解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * @param x Int
 * @return Int
 */
fun mySqrt(x: Int): Int {
    var ret = 0

    return ret
}
/** 69 end **/
