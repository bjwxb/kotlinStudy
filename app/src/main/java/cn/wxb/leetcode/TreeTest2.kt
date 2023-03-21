package cn.wxb.leetcode

import kotlin.math.max

/**
 * @desc: 二叉树相关
 * 100. 相同的树
 * 101. 对称二叉树
 * 104. 二叉树的最大深度
 * 111. 二叉树的最小深度
 * 222. 完全二叉树的节点个数
 * 226. 翻转二叉树
 * @author: wuxiaobo
 * @date: 2023/3/20 16:36
 */
class TreeTest2 {
}

fun main() {
//    test100()
//    test101()
//    test104()
//    test111()
    test222()
//    test226()
}

fun test100(){
    val left = TreeNode(1)
    left.left = TreeNode(2)
    left.left?.run {
        this.left = TreeNode(3)
        this.right = TreeNode(4)
    }
    left.right = TreeNode(2)
    left.right?.run {
        this.left = TreeNode(4)
        this.right = TreeNode(3)
    }

    val right = TreeNode(1)
    right.left = TreeNode(2)
    right.left?.run {
        this.left = TreeNode(3)
        this.right = TreeNode(4)
    }
    right.right = TreeNode(2)
    right.right?.run {
        this.left = TreeNode(4)
        this.right = TreeNode(3)
    }
    val ret = isSameTree(left, right)
    print(ret)
}

fun test101() {
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.left?.run {
        this.left = TreeNode(3)
        this.right = TreeNode(4)
    }
    root.right = TreeNode(2)
    root.right?.run {
        this.left = TreeNode(4)
        this.right = TreeNode(3)
    }
    val ret = isSymmetric(root)
    print(ret)
}

fun test104(){
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.left?.run {
        this.left = TreeNode(3)
        this.right = TreeNode(4)
    }
    print(maxDepth(root))
}

fun test111(){
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.run {
        this.left = TreeNode(21)
        this.right = TreeNode(3)
    }
    root.right?.right?.run {
        this.right = TreeNode(4)
    }
    root.right?.right?.right?.run {
        this.right = TreeNode(5)
    }
    print(minDepth(root))
}

fun test222(){
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.left?.run {
        this.left = TreeNode(3)
        this.right = TreeNode(4)
    }
    root.right = TreeNode(2)
    root.right?.run {
        this.left = TreeNode(4)
        this.right = TreeNode(3)
    }
    println(countNodes(root))
}
/**
 *       4
 *   2      7
 * 1   3   6  9
 */
fun test226() {
    val root = TreeNode(4)
    root.left = TreeNode(2)
    root.left?.run {
        this.left = TreeNode(1)
        this.right = TreeNode(3)
    }
    root.right = TreeNode(7)
    root.right?.run {
        this.left = TreeNode(6)
        this.right = TreeNode(9)
    }
    val res = invertTree(root)
    println(res?.`val`)
}

/** 100 start **/
/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * @param p TreeNode?
 * @param q TreeNode?
 * @return Boolean
 */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q != null) {
        return false
    } else if (p != null && q == null) {
        return false
    } else if (p == null && q == null) {
        return true
    } else {
        if (p!!.`val` != q!!.`val`) {
            return false
        }
        val outside = isSameTree(p.left, q.left)
        val inside = isSameTree(p.right, q.right)
        return outside && inside
    }
}
/** 100 end **/

/** 101 start **/
/**
 * 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * @param root TreeNode?
 * @return Boolean
 */
fun isSymmetric(root: TreeNode?): Boolean {
    if (null == root) {
        return false
    }
    return recursive101(root.left, root.right)
}

private fun recursive101(left: TreeNode?, right: TreeNode?): Boolean {
    if (left == null && right != null) {
        return false
    } else if (left != null && right == null) {
        return false
    } else if (left == null && right == null) {
        return true
    } else {
        if (left!!.`val` != right!!.`val`) {
            return false
        }
        val outside = recursive101(left.left, right.right)
        val inside = recursive101(left.right, right.left)
        return outside && inside
    }
}
/** 101 end **/

/** 104 start **/
/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * @param root TreeNode?
 * @return Int
 */
fun maxDepth(root: TreeNode?): Int {
    if(null == root){
        return 0
    }
    val leftHeight = maxDepth(root.left)
    val rightHeight = maxDepth(root.right)

    return max(leftHeight, rightHeight) + 1
}
/** 104 end **/

/** 111 start **/
/**
 * 给定一个二叉树，找出其最小深度。 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * @param root TreeNode?
 * @return Int
 */
fun minDepth(root: TreeNode?): Int {
    if(null == root){
        return 0
    }

    if(null == root.left && null == root.right){
        return 1
    }

    var minHeight = Int.MAX_VALUE
    root.left?.run {
        val minDepth = minDepth(root.left)
        minHeight = Math.min(minHeight, minDepth)
        println("minHeight = $minHeight , minDepth = $minDepth")
    }
    root.right?.run {
        val minDepth = minDepth(root.right)
        minHeight =  Math.min(minHeight, minDepth)
        println(">>>> minHeight = $minHeight , minDepth = $minDepth")
    }
    return minHeight + 1
}
/** 111 end **/

/** 222 start **/
/**
 * 222. 完全二叉树的节点个数
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~2的h次方个节点。
 */
fun countNodes(root: TreeNode?): Int {
    if(root == null){
        return 0
    }
    val leftNum = countNodes(root.left)
    val rightNum = countNodes(root.right)
    return leftNum + rightNum + 1
}
/** 222 end **/

/** 226 start **/
/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
 *      4
 *   2      7
 * 1   3   6  9
 * @param root TreeNode?
 * @return TreeNode?
 */
fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) {
        return root
    }
    val tmp = root.left
    root.left = root.right
    root.right = tmp
    invertTree(root.left)
    invertTree(root.right)
    return root
}

/** 226 end **/
