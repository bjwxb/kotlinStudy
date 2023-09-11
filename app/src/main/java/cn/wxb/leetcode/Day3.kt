package cn.wxb.leetcode

import java.lang.StringBuilder
import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * 第6题 N字形变换
 * 第7题 整数反转
 * 第8题 字符串转换整数（atoi）
 * 第9题 回文数
 * 第11题 盛水最多的容器
 * 13. 罗马数字转整数
 * 14. 最长公共前缀
 * 第15题 三数之和
 * 第18题 四数之和
 * 第20题 有效的括号
 *
 *
 * @author: wuxiaobo
 * @date: 2023/2/9 11:40
 */
class Day3 {
}

fun main() {
//    test6()
//    test7()
//    test8()
//    test11()
    test13()
//    test14()
//    test15()
//    test18()
//    test20()
}


/**
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 */
fun test6(){
    val s = "PAYPALISHIRING"
    val rows = 4
    val ret = convert(s, rows)
    println(ret)
}

fun test7() {
    val x = -1463847412//1463847412
    println(reverse(x))
}

fun test8(){

}

fun test13(){
    val s = "LVIII"//输出: 58  解释: L = 50, V= 5, III = 3.
    print(romanToInt(s))
}

fun test14(){
    val arr = arrayOf("flower","flow","flight")
    print(longestCommonPrefix(arr))
}

fun test18(){
    val arr = intArrayOf(1,0,-1,0,-2,2)
    val target = 0
    val ret = fourSum(arr, target)
    println(">> ${ret.toList()}")
}

fun test20(){
    val s = "()[]{}"
    println(isValid(s))
}

fun test15(){
    val arr = intArrayOf(-1,-8,-6,2,5,4,8,3,7)
    val ret = threeSum(arr)
    println(">>" + ret.toList())
}

fun test11(){
    val arr = intArrayOf(1,8,6,2,5,4,8,3,7)
    println(">>>> ${maxArea(arr)}")
}

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    if(nums.size < 3){
        return listOf()
    }
    nums.sort()
    val ret = mutableListOf<List<Int>>()
    for((index, value) in nums.withIndex()){
        if (value > 0){
            return ret
        }

        if(index > 0 && value == nums[index - 1]){
            continue
        }

        var left = index+1
        var right = nums.size - 1
        while (left < right){
            when {
                value + nums[left] + nums[right] > 0 -> {
                    right--
                }
                value + nums[left] + nums[right] < 0 -> {
                    left++
                }
                else -> {
                    val list = mutableListOf<Int>()
                    list.add(value)
                    list.add(nums[left])
                    list.add(nums[right])
                    ret.add(list)
                    while(left<right && nums[left] == nums[left+1]){
                        left++
                    }
                    while(left<right && nums[right] == nums[left-1]){
                        right--
                    }
                    right--
                    left++
                }
            }
        }
    }

    return ret

}

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0  输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 输入：nums = [2,2,2,2,2], target = 8   输出：[[2,2,2,2]]
 * @param nums IntArray
 * @param target Int
 * @return List<List<Int>>
 */
fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    nums.sort()
    val ret = mutableListOf<List<Int>>()
    for((i, v1) in nums.withIndex()){
        for((j, v2) in nums.withIndex()){
            var left = j+1
        }
    }


    return ret
}

/**
 * 8。
 * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数myAtoi(string s) 的算法如下：

 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231, 231− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231− 1 的整数应该被固定为 231− 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 */
fun myAtoi(s: String): Int {
    val ret = -1

    return ret
}

/**
 * 7。
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 * @param x Int  [-2147483648,2147483647]
 * @return Int
 */
fun reverse(x: Int): Int {
    var ret = 0
    var tmp = x
    while(tmp != 0){
        //取末位数
        val pop = tmp % 10
        //判断是否大于最大32位整数
        //2147483647
        if(ret > 214748364 || (ret == 214748364 && pop > 7)){
            return 0
        }
        //判断是否小于最小32位整数
        if(ret < Int.MIN_VALUE/10 || (ret == Int.MIN_VALUE/10 && pop < -8)){
            return 0
        }
        tmp /= 10
        ret = ret * 10 + pop
    }

    return ret
}

/**
 * 6. N字形变换
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 */
fun convert(s: String, numRows: Int): String {
    if(numRows < 2){
        return s
    }
    val ret = StringBuilder()
    val rowsStringBuilder = mutableListOf<StringBuilder>()
    for(i in 0..numRows){
        rowsStringBuilder.add(StringBuilder())
    }
    var i = 0
    var flag = -1
    for(c in s.toCharArray()){
        rowsStringBuilder[i].append(c)
        if(i == 0 || i == numRows - 1){
            flag = -flag
        }
        i += flag
    }
    for(sBuilder in rowsStringBuilder){
        ret.append(sBuilder)
    }
    return ret.toString()
}

/**
 * 9。回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 输入：x = 121 输出：true
 *
 * 输入：x = -121 输出：false
 *
 * 输入：x = 10
 * 输出：false
 */
fun isPalindrome(x: Int): Boolean {
    if(x < 0 || (x % 10 == 0 && x != 0)){
        return false
    }

    var tmp = x
    var revertNumber = 0
    while(tmp > revertNumber){
        revertNumber = revertNumber * 10 + tmp % 10
        tmp /= 10
    }

    //1221 - 12 == 12;     12321 - 12 == 123/12
    return tmp == revertNumber || revertNumber/10 == tmp
}

/**
 * 11. 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 *
 * 输入：height = [1,1]输出：1
 */
fun maxArea(height: IntArray): Int {
    var i = 0
    var j = height.size - 1
    var ret = 0

    while (i < j){
        //水的高度为较小边的高度
        val area = (j - i) * Math.min(height[i],height[j])
        ret = Math.max(ret, area)
        if(height[i] < height[j]){
            i++
        } else {
            j--
        }
    }
    return ret
}

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 输入：s = "()"  输出：true
 * 输入：s = "()[]{}"  输出：true
 * 输入：s = "(]"   输出：false
 */
fun isValid(s: String): Boolean {
    val stack = Stack<Char>()

    if(s.length and 1 == 1){
        return false
    }

    for(c in s){
        if(c == '(' || c == '[' || c == '{'){
            stack.push(c)
        } else {
            if(stack.isEmpty()){
                return false
            }
            if(c == ')'){
                if(stack.pop() != '('){
                    return false
                }
            }

            if(c == ']'){
                if(stack.pop() != '['){
                    return false
                }
            }

            if(c == '}'){
                if(stack.pop() != '{'){
                    return false
                }
            }
        }
    }

    return stack.isEmpty()
}

/** 13 start **/
/**
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，  D 和 M。
 *                       1   5   10 50 100 500  1000
 * @param s String
 * @return Int
 */
fun romanToInt(s: String): Int {
    var ret = 0
    val len = s.length
    for(i in 0 until len){
        val curNum = getNum(s[i])
        if(i + 1< len){
            val nextNum = getNum(s[i+1])
            if(curNum < nextNum){
                ret -= curNum
            } else {
                ret += curNum
            }
        } else {
            ret += curNum
        }
    }
    return ret
}

private fun getNum(c:Char):Int{
    return when(c){
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        'M' -> 1000
        else -> 0
    }
}
/** 13 end **/

/** 14 start **/
/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 输入：strs = ["flower","flow","flight"]  输出："fl"
 *
 * 输入：strs = ["dog","racecar","car"]  输出：""
 * @param strs Array<String>
 * @return String
 */
fun longestCommonPrefix(strs: Array<String>): String {
    if(strs.isEmpty()){
        return ""
    }
    var prefix = strs[0]
    for (i in 1 until strs.size){
        prefix = commonPrefix(prefix, strs[i])
        if(prefix.isEmpty()){
            break
        }
    }
    return prefix
}

private fun commonPrefix(str1:String, str2:String):String{
    var index = 0
    val len = Math.min(str1.length, str2.length)
    while(index < len && str1[index] == str2[index]){
        index++
    }
    return str1.substring(0, index)
}
/** 14 end **/
