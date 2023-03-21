package cn.wxb.leetcode

import java.lang.StringBuilder

/**
 * @desc:
 *      剑指 Offer 05. 替换空格
 *      剑指 Offer 58 - II. 左旋转字符串
 *      151. 反转字符串中的单词
 *      541. 反转字符串 II
 * @author: wuxiaobo
 * @date: 2023/3/3 18:07
 */
class StringTest {
}

fun main(){
//    testOffer05()
    testOffer58()
//    test151()
//    test541()
}

fun testOffer05(){
    val s = "hello world, hah"
    println(replaceSpace(s))
}

fun testOffer58(){
//    val s = "abcdefg"
    val s = "lrloseumgh"
    val k = 6
    println(reverseLeftWords(s, k))

}

fun test151(){
    val s = "the   sky   is blue"
//    val s = "  hello world  "
//    val s = "a good  example"
    println(reverseWords(s))
}

fun test541(){
    val s = "abcdefg"
    val k = 2
    println(reverseStr(s, k))
}

/**
 * offer05 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 输入：s = "We are happy."  输出："We%20are%20happy."
 * @param s String
 * @return String
 */
fun replaceSpace(s: String): String {
    val sBuilder = StringBuilder()
    s.forEach {
        if(it == ' '){
            sBuilder.append("%20")
        } else {
            sBuilder.append(it)
        }
    }

    return  sBuilder.toString()
}

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 输入: s = "abcdefg", k = 2  输出: "cdefgab"
 *
 * 输入: s = "lrloseumgh", k = 6   输出: "umghlrlose"
 */
fun reverseLeftWords(s: String, n: Int): String {
    val sb = StringBuilder()
    val size = s.length + n
    for(i in n until size){
        sb.append(s[i%s.length])
    }

    return sb.toString()
}

/**
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 输入：s = "the sky is blue"  输出："blue is sky the"
 *
 * 输入：s = "  hello world  "  输出："world hello"   解释：反转后的字符串中不能存在前导空格和尾随空格。
 *
 * 输入：s = "a good  example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 * @param s String
 * @return String
 */
fun reverseWords(s: String): String {
    val len = s.length
    val sb = StringBuilder()
    var i = len-1
    while(i >= 0){
        if(s[i] == ' '){//当前字符是空字符
            if(sb.isEmpty()){//如果stringBuilder是空的，则不追加，直接进行下次循环
                i--
            } else {
                //如果stringBuilder不为空，但是sb最后一个字符是空字符串，则至今进行下次循环
                if(sb[sb.length - 1] == ' ') {
                    i--
                } else {
                    sb.append(s[i])
                    i--
                }
            }
            continue
        }

        var r = i
        val tmp = i
        while(r >= 0 && s[r] != ' '){
            r--
            i--
        }
        sb.append(s.substring(r+1, tmp+1))

    }
    if(sb[sb.length - 1] == ' '){
        sb.deleteCharAt(sb.length - 1)
    }

    return sb.toString()
}

/**
 * 541. 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 输入：s = "abcdefg", k = 2  输出："bacdfeg"
 * 输入：s = "abcd", k = 2  输出："bacd"
 */
fun reverseStr(s: String, k: Int): String {
    val arr = s.toCharArray()
    val len = s.length
    var i = 0
    while(i < s.length){
        val end = Math.min(i+k, len)
        reverse(arr, i, end - 1)
        i+=k*2
    }
    return String(arr)
}

private fun reverse(arr:CharArray, l:Int, r:Int):String{
    var left = l
    var right = r
    while(left < right){
        val tmp = arr[left]
        arr[left] = arr[right]
        arr[right] = tmp
        left++
        right--
    }
    return String(arr)
}