package cn.wxb.leetcode

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @desc:回溯算法相关2
 * 46. 全排列
 * 332. 重新安排行程（困难）
 * 491. 递增子序列
 *
 * @author: wuxiaobo
 * @date: 2023/3/15 17:28
 */
class Day6 {
}

fun main() {
//    test491()
//    test46()
//    test47()
    test332()
}


fun test46(){
    val list = intArrayOf(1,2,3)
//    val list = intArrayOf(0,1)
//    val list = intArrayOf(1)
    val res = permute(list)
    println(res.toList().toString())
}

fun test47(){
//    val list = intArrayOf(1,1,2)
    val list = intArrayOf(2,2,1,1)
    val res = permuteUnique(list)
    println(res.toList().toString())
}

fun test332(){
    val tickets = mutableListOf<List<String>>().apply {
        add(arrayListOf("MUC", "LHR"))
        add(arrayListOf("JFK", "MUC"))
        add(arrayListOf("SFO", "SJC"))
        add(arrayListOf("LHR", "SFO"))
    }
    val ret = findItinerary(tickets)
    println(ret.toList().toString())
}


fun test491(){
    val list = intArrayOf(4,7,6,7)
    val res = findSubsequences(list)
    println(res.toList().toString())
}

/** 46 start **/
/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 输入：nums = [1,2,3]   输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 输入：nums = [0,1] 输出：[[0,1],[1,0]]
 * 输入：nums = [1] 输出：[[1]]
 *
 * @param nums IntArray
 * @return List<List<Int>>
 */
fun permute(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val len = nums.size
    val path = LinkedList<Int>()
    dfs46(nums, len, path, res)
    return res
}
private fun dfs46(nums:IntArray, len:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    if(path.size == len){
        res.add(ArrayList(path))
        return
    }

    for (i in 0 until len){
        if(path.contains(nums[i])){
            continue
        }
        path.addLast(nums[i])
        dfs46(nums, len, path, res)
        path.removeLast()
    }
}
/** 46 end **/

/** 47 start **/
/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * @param nums IntArray
 * @return List<List<Int>>
 */
fun permuteUnique(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val len = nums.size
    val path = LinkedList<Int>()
    val used = Array(len){false}
    nums.sort()
    dfs47(nums,used, len, path, res)
    return res
}

private fun dfs47(nums:IntArray, used:Array<Boolean>, len:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    if(path.size == len){
        res.add(ArrayList(path))
        return
    }

    for (i in 0 until len){
        if(i > 0 && nums[i] ==nums[i-1] && used[i-1]){
            continue
        }
        if(!used[i]){
            used[i] = true
            path.addLast(nums[i])
            dfs47(nums, used, len, path, res)
            path.removeLast()
            used[i] = false
        }

    }
}
/** 47 end **/

/** 332 start **/
/**
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 *
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 *
 * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 *
 * @param tickets List<List<String>>
 * @return List<String>
 */
fun findItinerary(tickets: List<List<String>>): List<String> {
    val res = mutableListOf<String>()

    return res
}
/** 332 end **/


/** 491 start **/
/**
 * 491
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 *
 * 输入：nums = [4,4,3,2,1]  输出：[[4,4]]
 *
 * 输入：nums = [4,6,7,7]  输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * @param nums IntArray
 * @return List<List<Int>>
 */
fun findSubsequences(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    val len = nums.size
    val path = LinkedList<Int>()
    dfs491(nums, 0, len, path, res)
    return res
}

private fun dfs491(nums:IntArray,begin:Int, len:Int, path:LinkedList<Int>, res:MutableList<List<Int>>){
    if(path.isNotEmpty() && path.size > 1){
        println("first >>== ${path.toList()}")
        res.add(ArrayList(path))
    }
    if(begin == len){
        return
    }

    val map = HashMap<Int, Int>()
    println("second >>> $begin, ${map.toList().toString()}")

    for(i in begin until len){
        if(map.containsKey(nums[i])){
            continue
        }
        println("third*** ${map.toList()}")
        if(path.isNotEmpty() && nums[i] < path.last){
            continue
        }
        map[nums[i]] = nums[i]
        path.addLast(nums[i])
        dfs491(nums, i+1, len, path, res)
        path.removeLast()
    }
}
/** 491 end **/