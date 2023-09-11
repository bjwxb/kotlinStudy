package cn.wxb.leetcode

import java.util.*
import kotlin.math.max

/**
 * @desc:
 *      26. 删除有序数组中的重复项
 *      27. 移除元素
 *      35. 搜索插入位置
 *      59. 螺旋矩阵II
 *      80. 删除有序数组中的重复项 II
 *      88. 合并两个有序数组
 *      209.长度最小的子数组
 *      704. 二分查找
 *      977. 有序数组的平方
 *      2357.使数组中所有元素都等于零
 * @author: wuxiaobo
 * @date: 2023/2/24 15:56
 */
class ArrayTest {
}

fun getWeekOfDate(dt: Date): String {
    val weekDays = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val cal: Calendar = Calendar.getInstance()
    cal.time = dt
    var w: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
    if (w < 0) w = 0
    return weekDays[w]
}

fun main(){
//    test26()
//    test27()
//    test35()
//    test59()
//    test80()
//    test88()
//    test209()
//    test704()
//    test977()
    test2357()
}

fun test26(){
    val nums = intArrayOf(1,2,2,2,2,3,4,4,5)
    println(removeDuplicates(nums))
}

fun test27(){
    val nums = intArrayOf(0,1,2,2,3,0,4,2)
//    val nums = intArrayOf(3,2,2,3)
    val target = 2

    val ret = removeElement(nums, target)
//    val ret = removeElement2(nums, target)
    println("$ret>> <<")
    for(i in 0 until ret){
        print("${nums[i]} ")
    }
}

fun test35(){
    val nums = intArrayOf(1, 3, 5, 6)
    val target = 7
    println(searchInsert(nums, target))
}

fun test59(){
    val n = 3
//    val retArr = generateMatrix(n)
    val retArr = generateMatrix2(n)
    retArr.forEach {
        println(it.toList())
    }
}

fun test80(){
    val arr = intArrayOf(1, 1, 1, 2, 2, 2, 3, 3, 4, 5)
    println(removeDuplicates80(arr))
    println(arr.toList())
}

fun test88(){
    val arr1 = intArrayOf(0)
    val m = 0
    val arr2 = intArrayOf(1)
    val n = 1
    merge(arr1, m, arr2, n)

    println(">> ${arr1.toList()}")
}

fun test209(){
    val nums = intArrayOf(2,3,1,2,4,3)
    val target = 7
    println(minSubArrayLen(target, nums))
}

fun test704(){
    val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
    val target = 9
    println(search(nums, target))
}

fun test977(){
    val nums = intArrayOf(-4,-1,0,3,10)
    println(sortedSquares(nums).toList())
}

fun test2357(){
    val nums = intArrayOf(1,5,0,3,5)
    println(minimumOperations(nums))
}

/** 26 start **/
/**
 * 26. 删除有序数组中的重复项
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次
 * ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * @param nums IntArray
 * @return Int
 */
fun removeDuplicates(nums: IntArray): Int {
    var slow = 0
    var fast = 1
    while (fast < nums.size){
        if(nums[fast] != nums[slow]){
            nums[++slow] = nums[fast]
        }
        fast++
    }
    return ++slow
}
/** 26 end **/

/**
 * 27. 移除元素
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 说明:为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 输入：nums = [3,2,2,3], val = 3  输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。
 * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    var left = 0
    var right = nums.size
    while (left < right){
        if(nums[left] == `val`){
            nums[left] = nums[right-1]
            right--
        } else {
            left++
        }
    }

    return left
}

fun removeElement2(nums: IntArray, `val`: Int): Int {
    var slowIndex = 0
    var fastIndex = 0
    while(fastIndex < nums.size){
        if(nums[fastIndex] != `val`){
            nums[slowIndex++] = nums[fastIndex]
        }
        fastIndex++
    }
    return slowIndex
}

/**
 * 35
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 输入: nums = [1,3,5,6], target = 5  输出: 2
 * 输入: nums = [1,3,5,6], target = 2  输出: 1
 * 输入: nums = [1,3,5,6], target = 7  输出: 4
 * 输入: nums = [1,3,5,6], target = 0  输出: 1
 */
fun searchInsert(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while(left <= right){
        val mid = left + (right - left)/2
        val num = nums[mid]
        if(num == target) {
            return mid
        } else if (num > target) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return left
}

/**
 * 59.螺旋矩阵II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 输入：n = 3  输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 输入：n = 1  输出：[[1]]
 *
 * 1 <= n <= 20
 */
fun generateMatrix(n: Int): Array<IntArray> {
    val maxNum = n*n
    var curNum = 1
    var row = 0
    var column = 0
    val directions:MutableList<IntArray> = mutableListOf<IntArray>().apply {
        add(intArrayOf(0, 1))
        add(intArrayOf(1, 0))
        add(intArrayOf(0, -1))
        add(intArrayOf(-1, 0))
    }
    val matrix = Array(n){IntArray(n){0} }
    var directionIndex = 0
    while(curNum <= maxNum){
        matrix[row][column] = curNum
        curNum++

        val nextRow = row + directions[directionIndex][0]
        val nextColumn = column + directions[directionIndex][1]
        /**
         * 如果满足一定条件，则换个方向填充
         * 1. 当nextColumn>=n时，证明已经到右边边界了，需要改变方向
         * 2. 当nextRow>=n 时，则已经到底部边界了，
         * 3. 当nextColumn < 0时，则到达左边边界了
         * 4. 当 nextRowM<0 则到达上边界了 (但是不会触发此条件)
         * 5. 当matrix[nextRow][nextColumn] != 0 则换方向
         */
        if(nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0){
            directionIndex = (directionIndex + 1) % 4
        }
        row += directions[directionIndex][0]
        column += directions[directionIndex][1]
    }

    return matrix
}

fun generateMatrix2(n: Int): Array<IntArray> {
    var curNum = 1
    val maxNum = n*n
    val matrix = Array(n){IntArray(n){0} }
    //左边界
    var left = 0
    //上边界
    var top = 0
    //右边界
    var right = n - 1
    //下边界
    var bottom = n - 1
    while(curNum <= maxNum){
        for(i in left..right){
            matrix[top][i] = curNum++
        }
        ++top
        for(i in top..bottom){
            matrix[i][right] = curNum++
        }
        --right

        for(i in right downTo left){
            matrix[bottom][i] = curNum++
        }
        --bottom

        for(i in bottom downTo top){
            matrix[i][left] = curNum++
        }
        ++left
    }

    return matrix
}


/** 80 start **/
/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * @param nums IntArray
 * @return Int
 */
fun removeDuplicates80(nums: IntArray): Int {
    val len = nums.size
    if(len < 3){
        return len
    }
    var slow = 2
    var fast = 2
    while (fast < len){
        if(nums[slow-2] != nums[fast]){
            nums[slow++] = nums[fast]
        }
        fast++
    }
    return slow
}
/** 80 end **/
/**
 * 88
 * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 */
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    //从后往前遍历，将最大值放到nums1末尾
    var p1 = m - 1
    var p2 = n - 1
    var tail = m + n - 1
    var curr = 0
    while(p1 > -1 || p2 > -1){
        curr = when {
            p1 < 0 -> {
                nums2[p2--]
            }
            p2 < 0 -> {
                nums1[p1--]
            }
            nums1[p1] > nums2[p2] -> {
                nums1[p1--]
            }
            else -> {
                nums2[p2--]
            }
        }
        nums1[tail--] = curr
    }
}

/**
 * 207.长度最小的子数组
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]  输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 输入：target = 4, nums = [1,4,4]  输出：1
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]  输出：0
 */
fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var ret = Int.MAX_VALUE
    var left = 0
    var right = 0
    var sum = 0
    while(right < nums.size){
        sum += nums[right]
        while(sum >= target){
            ret = Math.min(ret, right - left + 1)
            sum -= nums[left]
            left++
        }
        right++
    }

    return if(ret == Int.MAX_VALUE){0} else {ret}
}

/**
 * 704, 二分查找
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * 输入: nums = [-1,0,3,5,9,12], target = 9  输出: 4  解释: 9 出现在 nums 中并且下标为 4
 * 输入: nums = [-1,0,3,5,9,12], target = 2  输出: -1  解释: 2 不存在 nums 中因此返回 -1
 */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while(left <= right){
        val mid = left + (right - left) /2
        val num = nums[mid]
        if(num > target){
            right = mid - 1
        } else if (num < target){
            left = mid + 1
        } else {
            return mid
        }
    }

    return -1
}

/**
 * 977.有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 输入：nums = [-4,-1,0,3,10]  输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 */
fun sortedSquares(nums: IntArray): IntArray {
    val len = nums.size
    val ret = Array(len){0}
    var left = 0
    var right = len - 1
    var pos = len - 1
    while(left <= right){
        val lSquares = nums[left] * nums[left]
        val rSquares = nums[right] * nums[right]
        if(lSquares > rSquares){
            ret[pos] = lSquares
            ++left
        } else {
            ret[pos] = rSquares
            --right
        }
        --pos
    }
    return ret.toIntArray()
}

/**
 * 2357
 * 给你一个非负整数数组 nums 。在一步操作中，你必须：
 * 选出一个正整数 x ，x 需要小于或等于 nums 中 最小 的 非零 元素。
 * nums 中的每个正整数都减去 x。
 * 返回使 nums 中所有元素都等于 0 需要的 最少 操作数。
 *
 * 输入：nums = [1,5,0,3,5]
 * 输出：3
 * 解释：
 * 第一步操作：选出 x = 1 ，之后 nums = [0,4,0,2,4] 。
 * 第二步操作：选出 x = 2 ，之后 nums = [0,2,0,0,2] 。
 * 第三步操作：选出 x = 2 ，之后 nums = [0,0,0,0,0] 。
 */
fun minimumOperations(nums: IntArray): Int {
    var ret = 0
    val len = nums.size
    nums.sort()
    for((i, v) in nums.withIndex()){
        if(v > 0){
            ret++
            for(j in i until len){
                nums[j] = nums[j] - v
            }
        }
    }

    return ret
}