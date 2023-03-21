package cn.wxb.leetcode

import kotlin.math.max

/**
 * @desc:
 * 第1题 两数之和
 * 第2题 两数相加
 * 第3题 无重复字符的最长子串
 * @author: wuxiaobo
 * @date: 2023/1/7 17:37
 */
class ListNode(val value: Int) {
    var next: ListNode? = null
}

fun main() {
    test3()
}

//第一题 test
fun test1() {
    val array = intArrayOf(2, 4, 8, 7)
    val target = 12
    val ret = twoSum(array, target)
    println(">>>>>> ret = ${ret[0]},${ret[1]}")
}

fun test2() {
    val l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(5)

    val l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next?.next = ListNode(4)

    var ln = addTwoNumbers(l1, l2)
    while (ln != null) {
        print(" ${ln.value} ")
        ln = ln.next
    }
}

fun test3() {
    val s = "abcabcbb"
    print(">>>>> maxLength = ${lengthOfLongestSubstring(s)}")
}

/**
 * 3。无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
fun lengthOfLongestSubstring(s: String): Int {
    val len = s.length
    var maxLen = 0
    var rk = -1
    val set = hashSetOf<Char>()
    for (index in s.indices) {
        if (index != 0) {
            //移除左边第一个字符
            set.remove(s[index - 1])
        }

        while (rk + 1 < len && !set.contains(s[rk + 1])) {
            set.add(s[rk + 1])
            rk++
        }
        maxLen = maxLen.coerceAtLeast(rk - index + 1)
    }
    return maxLen
}

/**
 * 2. 两数相加
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val head = ListNode(0)
    var tail = head
    var carry = 0
    var ln1 = l1
    var ln2 = l2

    while (ln1 != null || ln2 != null) {
        val n1 = ln1?.value ?: 0
        val n2 = ln2?.value ?: 0
        val sum = n1 + n2 + carry
        carry = sum / 10
        tail.next = ListNode(sum % 10)
        tail = tail.next!!

        ln1?.run { ln1 = this.next }
        ln2?.run { ln2 = this.next }
    }
    if (carry > 0) {
        tail.next = ListNode(carry)
    }

    return head.next
}

/**
 * 第1题
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 */
fun twoSum(numArray: IntArray, target: Int): IntArray {
    val ret = intArrayOf(-1, -1)
    val map = hashMapOf<Int, Int>()
    for ((index, value) in numArray.withIndex()) {
        val temp = target - value
        if (map.containsKey(temp)) {
            ret[0] = map[temp]!!
            ret[1] = index
            break
        }
        map[value] = index
    }

    return ret
}