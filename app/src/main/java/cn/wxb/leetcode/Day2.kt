package cn.wxb.leetcode

/**
 * @desc:
 * 4. 寻找两个正序数组的中位数
 * 5. 最长回文字串
 * @author: wuxiaobo
 * @date: 2023/1/10 20:31
 */
class Day2 {
}

fun main(){
//    test4()
    test5()
}

fun test4(){
    val intArray1 = intArrayOf(1, 3)
    val intArray2 = intArrayOf(2)
    print(">>>>> ${findMedianSortedArrays(intArray1, intArray2)}")
}

fun test5(){
    val str = "cbbd" //cbbd
    println(">>> ${longestPalindrome(str)}")
}

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val ret = 0.0

    return ret
}

/**
 * 5. 最长回文字串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * 输入：s = "babad"  输出："bab"  解释："aba" 同样是符合题意的答案。
 * 输入：s = "cbbd" 输出："bb"
 */
fun longestPalindrome(s: String): String {
    var start = 0
    var end = 0

    for(i in s.indices){
        val oddLen = getPalindromeLength(s, i, i)
        val evenLen = getPalindromeLength(s, i, i+1)
        val len = Math.max(oddLen, evenLen)
        println("oddLen = $oddLen, evenLen = $evenLen")
        if(len > end - start){
            start = i - (len - 1) / 2
            end = i + len/2
        }
    }

    return s.substring(start, end+1)
}

fun getPalindromeLength(s: String, left: Int, right: Int): Int {
    var l = left
    var r = right
    while (l >= 0 && r < s.length && s[l] == s[r]) {
        l--
        r++
    }

    //因为while循环里，最后不满足条件时，比目标数组的长度大2
    return (r - l + 1) - 2
}