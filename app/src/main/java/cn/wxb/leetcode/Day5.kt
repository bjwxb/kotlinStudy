package cn.wxb.leetcode

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * @desc: 回溯算法相关1， 回溯剩下相关算法题 在day6.kt文件中
 * 17. 电话号码的字母组合
 * 39. 组合总和
 * 40. 组合总和 II
 * 77. 组合
 * 78. 子集
 * 90. 子集 II
 * 93. 复原 IP 地址
 * 131. 分割回文串
 * 216. 组合总和 III
 *
 * @author: wuxiaobo
 * @date: 2023/3/9 20:40
 */
class Day5 {
}

fun main() {
//    test17()
//    test39()
//    test40()
    test77()
//    test78()
//    test90()
//    test93()
//    test131()
//    test216()
}

fun test17(){
    val digits = "23"
    val ret = letterCombinations(digits)
    println(ret.toList().toString())
}

fun test39(){
    val intArray = intArrayOf(2,3,6,7)
    val target = 7
    val ret = combinationSum(intArray, target)
    println(ret.toList().toString())
}

fun test40(){
//    val intArray = intArrayOf(10,1,2,7,6,1,5)
    val intArray = intArrayOf(2, 5, 2, 1, 2)
    val target = 5
    val ret = combinationSum2(intArray, target)
    println(ret.toList().toString())
}

fun test77(){
    val n = 4
    val k = 2
    val ret = combine(n, k)
    println(ret.toList().toString())
}

fun test78(){
    val list = intArrayOf(1,2,3)
    val res = subsets(list)
    println(res.toList().toString())
}

fun test90(){
//    val list = intArrayOf(1,2,2)
    val list = intArrayOf(4, 4, 4, 1, 4)
    val res = subsetsWithDup(list)
    println(res.toList().toString())
}

fun test93(){
    val s = "25525511135"
//    val s = "101023"
    println(restoreIpAddresses(s).toList().toString())
}

fun test131(){
    val s = "aab"
    println(partition(s))
}

fun test216(){
    val k = 3
    val n = 7
    val ret = combinationSum3(k, n)
    println(ret.toList().toString())
}

/** 17 start **/
/**
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 输入：digits = "23"  输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 输入：digits = ""  输出：[]
 *
 * 输入：digits = "2"  输出：["a","b","c"]
 *
 *
 * 0 <= digits.length <= 4   digits[i] 是范围 ['2', '9'] 的一个数字。
 * @param digits String
 * @return List<String>
 */
fun letterCombinations(digits: String): List<String> {
    val list = mutableListOf<String>()
    if(digits.isEmpty()){
        return list
    }
    val map = hashMapOf<String, String>().apply {
        put("2", "abc")
        put("3", "def")
        put("4", "ghi")
        put("5", "jkl")
        put("6", "mno")
        put("7", "pqrs")
        put("8", "tuv")
        put("9", "wxyz")
    }
    val index = 0
    val path = StringBuilder()
    val len = digits.length
    dfs17(len , digits, map, index, path, list)

    return list
}

private fun dfs17(len:Int, digits: String, map:HashMap<String, String>, index:Int,path:StringBuilder, res:MutableList<String>){
    if(index == len){
        res.add(path.toString())
        return
    }
    if(index < 0 || index >= len){
        return
    }
    val num = digits[index].toString()
    val letter = map.getValue(num)
    if(letter.isNotEmpty()){
        for(c in letter){
            path.append(c)
            dfs17(len, digits, map, index+1, path, res)
            path.deleteCharAt(path.length - 1)
        }
    }
}
/** 17 end **/

/** 39 start **/
/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合
 * ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 *
 * 输入：candidates = [2,3,6,7], target = 7  输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 输入: candidates = [2,3,5], target = 8  输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 输入: candidates = [2], target = 1  输出: []
 *
 * @param candidates IntArray
 * @param target Int
 * @return List<List<Int>>
 */
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val len = candidates.size
    val res = mutableListOf<List<Int>>()
    if(len == 0){
        return res
    }

    candidates.sort()
    val path = LinkedList<Int>()
    dfs(candidates, 0, len, target, path, res)

    return res
}

private fun dfs(candidates:IntArray, begin:Int, len:Int, target:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    //target为0时 不再产生新的孩子结点
    if(target == 0){
        res.add(ArrayList<Int>(path))
        return
    }
    for(i in begin until len){
        if(target - candidates[i] < 0){
            break
        }
        path.addLast(candidates[i])
        println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]))
        dfs(candidates, i, len, target - candidates[i], path, res)
        path.removeLast()
        println("递归之后 => $path")
    }
}
/** 39 end **/

/** 40 start **/
/**
 * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * @param candidates IntArray
 * @param target Int
 * @return List<List<Int>>
 */
fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val len = candidates.size
    val res = mutableListOf<List<Int>>()
    if(len == 0){
        return res
    }
    val path = LinkedList<Int>()
    candidates.sort()
    dfs40(0, candidates, len, target, path, res)
    return res
}
private fun dfs40(begin:Int, candidates: IntArray, len:Int,target:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    if(target == 0){
        res.add(ArrayList(path))
        return
    }
    for(i in begin until len){
        val num = candidates[i] // 1
        if(target - num < 0){
            break
        }
        //去重操作
        if(i > begin && num == candidates[i - 1]){
            continue
        }
        path.addLast(num) //1
        dfs40(i+1, candidates, len, target - num, path, res)
        path.removeLast()
    }
}
/** 40 end **/

/** 77 start **/
/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 你可以按 任何顺序 返回答案。
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * 输入：n = 1, k = 1 输出：[[1]]
 * @param n Int
 * @param k Int
 * @return List<List<Int>>
 */
fun combine(n: Int, k: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val path = LinkedList<Int>()
    backtracking(1, n, k, path, res)
    return res
}

private fun backtracking(begin:Int, len:Int, k:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){

    if(path.size == k){
        res.add(ArrayList<Int>(path))
        return
    }
    for(i in begin..len){
        path.addLast(i)
        println("递归之前 => $path")
        backtracking(i+1, len, k, path, res)
        path.removeLast()
        println("递归之后 => $path")
    }
}
/** 77 end **/

/** 78 start **/
/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 输入：nums = [1,2,3]   输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 输入：nums = [0]  输出：[[],[0]]
 * @return List<List<Int>>
 */
fun subsets(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val path = LinkedList<Int>()
    val len = nums.size
    dfs78(nums, 0, len, path, res)
    return res
}

private fun dfs78(nums:IntArray,begin:Int, len:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    res.add(ArrayList(path))

    if(begin == len){
        return
    }

    for(i in begin until len){
        path.addLast(nums[i])
        dfs78(nums, i+1, len, path, res)
        path.removeLast()
    }
}
/** 78 end **/

/** 90 start **/
/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列
 *
 * 输入：nums = [1,2,2]  输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 输入：nums = [0]  输出：[[],[0]]
 * @param nums IntArray
 * @return List<List<Int>>
 */
fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val path = LinkedList<Int>()
    val len = nums.size
    nums.sort()
    dfs90(nums, 0, len, path, res)
    return res
}

private fun dfs90(nums:IntArray,begin:Int, len:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    res.add(ArrayList(path))//[][1][1,2][1,2,2]
    //println("first === $begin>>>> ${ArrayList(path).toList()}")
    if(begin == len){//0,1,2
        return
    }
    //去重操作 第二种map方式
//    val map = HashMap<Int, Int>()
    for(i in begin until len){
        //todo 去重操作条件判断,前提条件数组需要有序
        if(i > begin && nums[i] == nums[i - 1]){
            continue
        }
//        if(map.containsKey(nums[i])){
//            continue
//        }
//        map.put(nums[i], nums[i])
        path.addLast(nums[i])//[1][2][2]
        //println("second>> i = $i, begin=$begin, path = ${path.toList().toString()}")
        dfs90(nums, i+1, len, path, res)
        path.removeLast()//[1][2]
        //println(">>> third >>> ${path.toList()}")
    }
}
/** 90 end **/

/** 93 start **/
/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入'.' 来形成。
 * 你 不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 输入：s = "25525511135"   输出：["255.255.11.135","255.255.111.35"]
 *
 * 输入：s = "101023"  输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * 1 <= s.length <= 20   s 仅由数字组成
 * @param s String
 * @return List<String>
 */
fun restoreIpAddresses(s: String): List<String> {
    val res = mutableListOf<String>()
    if(s.length < 4 || s.length > 12){
        return res
    }
    val path = LinkedList<String>()
    val len = s.length
    dfs93(0, len, 0, s, path, res)

    return res
}

private fun dfs93(begin:Int, len:Int, splitTime:Int, s:String, path:LinkedList<String>, res:MutableList<String>){
    if(splitTime == 4){
        if(begin == len){
            val ip = StringBuilder()
            for(p in path){
                ip.append("$p.")
            }
            ip.deleteCharAt(ip.lastIndex)
            res.add(ip.toString())
            return
        }
    }

    if(begin == len || path.size == 4){
//        //println("$splitTime>> begin = $begin, path = ${path.toList().toString()}")
        return
    }

    for(i in begin until len){
        val subStr = s.substring(begin, i+1)
        val endStr = s.subSequence(i, len)
        if(path.size == 1 && endStr.length > 9){
            break
        }
        if(path.size == 2 && endStr.length > 6){
            break
        }
        if(path.size == 3 && endStr.length > 3){
            break
        }
        if(subStr.length > 3){
            break
        }
        if(subStr.length > 1 && subStr.startsWith("0")){
            break
        }
        if(isIpSegment(subStr)){
            //println("${path.size}>>>> $subStr")
            path.addLast(subStr)
            dfs93(i+1, len, splitTime+1, s, path, res)
            path.removeLast()
        }
    }
}

private fun isIpSegment(str:String):Boolean{
    if(str.isEmpty()){
        return false
    }
    val num = str.toInt()
    if(num > 255){
        return false
    }

    return true
}

/** 93 end **/

/** 131 start **/
/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 输入：s = "aab"  输出：[["a","a","b"],["aa","b"]]
 *
 * 输入：s = "a"  输出：[["a"]]
 */
fun partition(s: String): List<List<String>> {
    val res = mutableListOf<List<String>>()
    if(s.isEmpty()){
        return res
    }
    val path = LinkedList<String>()
    dfs131(0, s, path, res)
    return res
}

private fun dfs131(begin:Int, s:String,path:LinkedList<String>, res:MutableList<List<String>>){

    if(begin >= s.length){
        res.add(ArrayList(path))
        return
    }

    for(i in begin until s.length){
        val subStr = s.substring(begin, i+1)
        if(isHuiWen(subStr)){
            path.addLast(subStr)
        } else {
            continue
        }
        dfs131(i + 1, s, path, res)
        path.removeLast()
    }
}

private fun isHuiWen(s:String):Boolean{
    if(s.isEmpty()){
        return false
    }
    var left = 0
    var right = s.length - 1
    while (left < right){
        if(s[left] == s[right]){
            left++
            right--
        } else {
            return false
        }
    }
    return true
}
/** 131 end **/

/** 216 start **/
/**
 * 找出所有相加之和为n 的k个数的组合，且满足下列条件：
 * 只使用数字1到9, 每个数字最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * 输入: k = 3, n = 7  输出: [[1,2,4]]
 * 解释: 1 + 2 + 4 = 7 没有其他符合的组合了。
 *
 * 输入: k = 3, n = 9  输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了
 *
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 * @param k Int
 * @param n Int
 * @return List<List<Int>>
 */
fun combinationSum3(k: Int, n: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val path = LinkedList<Int>()
    dfs216(1, n, k, path, res)
    return res
}

private fun dfs216(begin:Int, target: Int, k:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){

    if(path.size == k && target == 0){
        res.add(ArrayList<Int>(path))
        return
    }

    for(i in begin..9){
        if(target - i < 0){
            break
        }
        path.addLast(i)
        dfs216(i+1, target - i, k, path, res)
        path.removeLast()
    }
}
/** 216 end **/
