package cn.wxb.leetcode

/**
 * @desc: hash相关算法
 *      第202题. 快乐数
 *      242. 有效的字母异位词
 *      383. 赎金信
 *
 * @author: wuxiaobo
 * @date: 2023/2/14 10:38
 */
class HashTest {
}

fun main(){
    test202()
//    test242()
//    test383()
}

fun test202(){
    val num = 19
    println(isHappy(num))
}

fun test242(){
    val s = "anagram"
    val t = "nagaram"
    println(isAnagram(s, t))
}

fun test383(){
    val s = "aa"
    val t = "aab"
    println(canConstruct(s, t))
}

/**
 * 第202题. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 */
fun isHappy(n: Int): Boolean {
    var slow = n
    var fast = getNext(n)
    while (fast != 1 && slow != fast){
        slow = getNext(slow)
        fast = getNext(getNext(fast))
    }

    return fast == 1
}

private fun getNext(n:Int):Int{
    var ret = 0
    var num = n
    while(num > 0){
        val remainder = num % 10
        ret += remainder * remainder
        num /= 10
    }

    return ret
}

/**
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * ransomNote 和 magazine 由小写英文字母组成
 * @return Boolean
 */
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    if(ransomNote.length > magazine.length){
        return false
    }

    val array = Array(26){0}
    for (c in magazine){
        array[c - 'a']++
    }

    for(c in ransomNote){
        array[c - 'a']--
        if(array[c - 'a'] < 0) {
            return false
        }
    }

    return true
}

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词
 */
fun isAnagram(s: String, t: String): Boolean {

    if(s.length != t.length) {
        return false
    }

    val arr = Array(26) { 0 }
    for(c in s){
        arr[c - 'a']++
    }
    for(c in t){
        arr[c - 'a']--
        if(arr[c - 'a'] < 0){
            return false
        }
    }

    return true
}