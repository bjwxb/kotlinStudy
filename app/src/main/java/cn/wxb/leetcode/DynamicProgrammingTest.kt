package cn.wxb.leetcode

import kotlin.math.cos

/**
 * @desc: 动态规划相关 - 如果某一问题有很多重叠子问题，使用动态规划是最有效的。
 * 动态规划中每一个状态一定是由上一个状态推导出来的，这一点就区分于贪心，贪心没有状态推导，而是从局部直接选最优的
 *
 * 509. 斐波那契数
 * 70. 爬楼梯
 * 746. 使用最小花费爬楼梯
 * 62. 不同路径
 * 63. 不同路径 II
 * 343. 整数拆分
 * @author: wuxiaobo
 * @date: 2023/3/21 10:42
 */
class DynamicProgrammingTest {
}

fun main() {
//    test62()
//    test63()
//    test70()
    test343()
//    test509()
//    test746()
}

fun test62(){
    val m = 3
    val n = 7
    println(uniquePaths(m, n))
}

fun test63(){
    val obstacleGrid = Array(3){IntArray(4){0}}
    obstacleGrid[1][1] = 1
    println(uniquePathsWithObstacles(obstacleGrid))
}

fun test70(){
    println(climbStairs(5))
}

fun test343(){
    println(integerBreak(10))
}

fun test509(){
    println(fib(9))
}

fun test746(){
    val cost = intArrayOf(1,100,1,1,1,100,1,1,100,1)
    val ret = minCostClimbingStairs(cost)
    println(ret)
}

/** 62 start **/
/**
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 输入：m = 3, n = 2  输出：3
 * 输入：m = 7, n = 3  输出：28
 * 输入：m = 3, n = 3  输出：6
 *
 * m1,n1  1
 * m2,n2  2
 * m3,n2  3
 * 1. 确定dp[i, j] 代表由[0, 0] 到 [j, j]有d[i,j]条路径
 * 2. dp[i, j] = dp[i-1,j] + dp[i, j-1]
 * 3. 确定初始化数值 dp[i, 0] = 1, dp[0,j] = 1
 * 4. 确定遍历顺序
 * 5. 举例推导
 *
 * 1  1  1  1  1  1  1
 * 1  2  3  4  5  6  7
 * 1  3  6  10 15 21 28
 * @param m Int
 * @param n Int
 * @return Int
 */
fun uniquePaths(m: Int, n: Int): Int {
    val dp = Array(m){ IntArray(n)}
    //第一列路径全是1
    for(i in 0 until m){
        dp[i][0] = 1
    }
    //第一行路径全是1
    for(i in 0 until n){
        dp[0][i] = 1
    }


    for(i in 1 until m){
        for (j in 1 until n){
            dp[i][j] = dp[i-1][j] + dp[i][j-1]
        }
    }
    return dp[m-1][n-1]
}
/** 62 end **/

/** 63 start **/
/**
 * 63. 不同路径 II
 * 相比62题，现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * @param obstacleGrid Array<IntArray>
 * @return Int
 */
fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    val m = obstacleGrid.size
    val n = obstacleGrid[0].size
    val dp = Array(m){IntArray(n){0} }
    for(i in 0 until m){
        if(obstacleGrid[i][0] == 1){
            break
        }
        dp[i][0] = 1
    }
    for(i in 0 until n){
        if(obstacleGrid[0][i] == 1){
            break
        }
        dp[0][i] = 1
    }

    for(i in 1 until m){
        for(j in 1 until n){
            if(obstacleGrid[i][j] == 1){
                continue
            }
            dp[i][j] = dp[i-1][j] + dp[i][j-1]
        }
    }
    return dp[m-1][n-1]
}
/** 63 end **/

/** 70 start **/
/**
 * climb - 攀爬， stair - 楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 *
 * 输入：n = 3 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * n = 4, 输出5
 * 1 1 1 1
 * 1 2 1
 * 2 1 1
 * 1 1 2
 * 2 2
 *
 * F(1)=1, F(2)=2, F(3)=3, F(4)=5， F(5) = 8
 */
fun climbStairs(n: Int): Int {
    if(n < 3){
        return n
    }
    var p = 1
    var q = 2
    var r = 0
    for(i in 3..n){
        r = p+q
        p = q
        q = r
    }
    return r
}
/** 70 end **/

/** 343 start **/
/**
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 * 返回 你可以获得的最大乘积 。
 *
 * 输入: n = 2  输出: 1  解释: 2 = 1 + 1, 1 × 1 = 1。
 * 输入: n = 10  输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 1. 确定dp[i]含义
 * 2. 确定推导公式
 * 3. 确定初始化数值
 * 4. 遍历顺序
 * 5. 举例推导
 *
 * 1 1*0 = 0
 * 2 1*1 = 1
 * 3 1+1+1 1+2 1*2 = 2
 * 4 2*2
 * @param n Int
 * @return Int
 */
fun integerBreak(n: Int): Int {
    if(n < 2){
        return 0
    }
    //dp[i]代表正整数n拆分后的结果最大积
    val dp = IntArray(n+1)
    dp[2] = 1
    for(i in 3..n){
        var j = 1
        while (j <= (i - j)){
            // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
            // j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
            dp[i] = Math.max(dp[i], Math.max(j * (i-j), j*dp[i-j]))
            println("i = $i, j = $j, dp[$i] = ${dp[i]}")
            j++
        }
    }
    return dp[n]
}
/** 343 end **/

/** 509 start **/
/**
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 *
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
 * @param n Int
 * @return Int
 */
fun fib(n: Int): Int {
    if(n < 2){
        return n
    }
    var p = 0
    var q = 1
    var r = 0
    for(i in 2..n){
        r = p + q
        p = q
        q = r
    }
    return r
}
/** 509 end **/

/** 746 start **/
/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 * 输入：cost = [10,15,20] 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。 总花费为 15 。
 *
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]  输出：6
 *      dp   = [0,1,  2,2,
 * 解释：你将从下标为 0 的台阶开始。
 *
 * @param cost IntArray
 * @return Int
 */
fun minCostClimbingStairs(cost: IntArray): Int {
    val n = cost.size
    var dp1 = 0
    var dp2 = 0
    var dpi = 0
    for (i in 2..n) {
        dpi = Math.min(dp1 + cost[i - 2], dp2 + cost[i - 1])
        dp1 = dp2
        dp2 = dpi
    }
    return dp2
}
/** 746 end **/

