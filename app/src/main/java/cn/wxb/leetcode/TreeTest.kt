package cn.wxb.leetcode

import java.util.*

/**
 * @desc: 二叉树相关
 * 深度优先遍历
 * 94. 二叉树的中序遍历
 * 144. 二叉树的前序遍历
 * 145. 二叉树的后序遍历
 *
 * 广度优先遍历 - 层序遍历
 * 102. 二叉树的层序遍历
 * 107. 二叉树的层序遍历 II
 * 99. 二叉树的右视图
 * @author: wuxiaobo
 * @date: 2023/3/20 09:21
 */
class TreeTest {
}

class TreeNode(val `val`:Int){
    var left:TreeNode? = null
    var right:TreeNode? = null
}

fun main() {
//    test94()
//    test144()
//    test145()

//    test102()
//    test107()
    test99()
}

fun test94(){
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.left = TreeNode(3)
//    val ret = inorderTraversal(root)//递归
    val ret = inorderTraversal2(root)//迭代
    println(ret.toList())
}

fun test144(){
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.left = TreeNode(3)
//    val ret = preorderTraversal(root)//递归实现
    val ret = preorderTraversal2(root)//迭代实现
    println(ret.toList())
}

fun test145(){
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.left = TreeNode(3)
//    val ret = postorderTraversal(root)
    val ret = postorderTraversal2(root)
    println(ret.toList())
}

fun test99(){

    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.left?.run {
        this.right = TreeNode(5)
    }
    root.right = TreeNode(3)
    root.right?.run {
        this.right = TreeNode(4)
    }
    val ret = rightSideView(root)
    println(ret.toList())
}

/**
 *     3
 *  9    20
 *     15   7
 */
fun test102(){
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right?.run {
        this.left = TreeNode(15)
        this.right = TreeNode(7)
    }
    val ret = levelOrder(root)
    println(ret.toList())
}
fun test107(){
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right?.run {
        this.left = TreeNode(15)
        this.right = TreeNode(7)
    }
    val ret = levelOrderBottom(root)
    println(ret.toList())
}

/** 144 start **/
/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 前序 - 中左右
 * 中序 - 左中右
 * 后序 - 左右中
 * @param root TreeNode?
 * @return List<Int>
 */
fun preorderTraversal(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    preorder(root, list)
    return list
}

//跌点实现 前序 遍历
fun preorderTraversal2(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    if(null == root){
        return list
    }

    val stack = Stack<TreeNode>()
    stack.push(root)
    while(!stack.empty()){
        val node = stack.pop()
        list.add(node.`val`)
        node.right?.run {
            stack.push(this)
        }
        node.left?.run {
            stack.push(this)
        }
    }

    return list
}

private fun preorder(node:TreeNode?, list:MutableList<Int>){
    if(node == null){
        return
    }
    list.add(node.`val`)
    preorder(node.left, list)
    preorder(node.right, list)
}
/** 144 end **/

/** 145 start **/
/**
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历
 * 前序 - 中左右
 * 中序 - 左中右
 * 后序 - 左右中
 * @param root TreeNode?
 * @return List<Int>
 */
fun postorderTraversal(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    postorder(root, list)
    return list
}

//迭代方式遍历 后序 - 左右中
fun postorderTraversal2(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    if(null == root){
        return list
    }

    val stack = Stack<TreeNode>()
    stack.push(root)
    while (!stack.isEmpty()){
        var node = stack.pop()
        if(node != null){
            stack.push(node)
            stack.push(null)
            node.right?.run { stack.push(this) }
            node.left?.run { stack.push(this) }
        } else {
            node = stack.pop()
            list.add(node.`val`)
        }
    }

    return list
}

private fun postorder(node:TreeNode?, list:MutableList<Int>){
    if(null == node){
        return
    }
    postorder(node.left, list)
    postorder(node.right, list)
    list.add(node.`val`)
}
/** 145 end **/

/** 94 start **/
/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 前序 - 中左右
 * 中序 - 左中右
 * 后序 - 左右中
 */
fun inorderTraversal(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    inorder(root, list)
    return list
}

//迭代实现 中序 遍历 中序 - 左中右
fun inorderTraversal2(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    if(null == root){
        return list
    }
    val stack = Stack<TreeNode>()
    var curr = root
    while (curr != null || !stack.isEmpty()){
        if(curr != null){
            stack.push(curr)
            curr = curr.left
        } else {
            curr = stack.pop()
            list.add(curr.`val`)
            curr = curr?.right
        }
    }
    return list
}

private fun inorder(node:TreeNode?, list:MutableList<Int>){
    if(null == node){
        return
    }
    inorder(node.left, list)
    list.add(node.`val`)
    inorder(node.right, list)
}
/** 94 end **/

/** 102 start **/
/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * @param root TreeNode?
 * @return List<List<Int>>
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val res = mutableListOf<MutableList<Int>>()

    recursive102(root, 0, res)

    return res
}

private fun recursive102(root:TreeNode?, deep:Int, res:MutableList<MutableList<Int>>){
    if(root == null){
        return
    }
    val d = deep + 1
    if(res.size < d){
        res.add(mutableListOf())
    }
    res[d - 1].add(root.`val`)
    recursive102(root.left, d, res)
    recursive102(root.right, d, res)
}
/** 102 end **/

/** 107 start **/
/**
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 输入：root = [3,9,20,null,null,15,7]  输出：[[15,7],[9,20],[3]]
 * @param root TreeNode?
 * @return List<List<Int>>
 */
fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    val res = LinkedList<MutableList<Int>>()

    recursive102(root, 0, res)
//    bfs107(root, 1, res)
//    return res
    return res.reversed()
}

private fun bfs107(node:TreeNode?, deep:Int, res:LinkedList<MutableList<Int>>){
    if(node == null){
        return
    }
    if(res.size < deep){
        res.addFirst(mutableListOf())
    }
    res[res.size - deep].add(node.`val`)
    bfs107(node.left, deep+1, res)
    bfs107(node.right, deep+1, res)
}
/** 107 end **/

/** 99 start **/
/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 输入: [1,2,3,null,5,null,4]  输出: [1,3,4]
 * @param root TreeNode?
 * @return List<Int>
 */
fun rightSideView(root: TreeNode?): List<Int> {
    val res = mutableListOf<Int>()
    val list = mutableListOf<MutableList<Int>>()
    recursive99(root, 1, list)
    list.filter { it.isNotEmpty() }.forEach {
        res.add(it.last())
    }
    return res
}

private fun recursive99(root:TreeNode?, deep:Int, res:MutableList<MutableList<Int>>){
    if(root == null){
        return
    }
    if(res.size < deep){
        res.add(mutableListOf())
    }
    res[deep - 1].add(root.`val`)
    recursive99(root.left, deep+1, res)
    recursive99(root.right, deep+1, res)
}
/** 99 end **/

