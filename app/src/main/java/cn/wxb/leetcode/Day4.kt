package cn.wxb.leetcode

/**
 * @desc:
 *      19.删除链表的倒数第n个节点
 *      leetcode 203.移除链表元素
 *      leetcode 206.反转链表
 *      leetcode 344.字符串反转
 * @author: wuxiaobo
 * @date: 2023/2/10 17:43
 */
class Day4 {
}

fun main() {
    val a = 13
    if(a and 1 == 1){
        println("$a 是奇数")
    } else {
        println("$a 是偶数")
    }
    println(a and 1 == 0)
    test19()
//    test203()
//    test206()
//    test344()
}

fun test19(){
    val l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(5)
    l1.next?.next?.next = ListNode(7)
    var ln = removeNthFromEnd(l1, 1)
    while (ln != null) {
        print(" ${ln.`val`} ")
        ln = ln.next
    }
}

/**
 * 19. 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {

    val dummy = ListNode(-1)
    dummy.next = head
    var fast = head
    var slow:ListNode? = dummy
    for(i in 1..n){
        fast = fast?.next
    }
    while(fast != null){
        fast = fast.next
        slow = slow?.next
    }
    slow?.next = slow?.next?.next

    return dummy.next
}

fun test203(){
    val l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(5)
    l1.next?.next?.next = ListNode(7)
    var ln = removeElements(l1, 7)
    while (ln != null) {
        print(" ${ln.`val`} ")
        ln = ln.next
    }
}

/**
 * 203. 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */
fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    val pre = ListNode(-1)
    pre.next = head
    var cur:ListNode? = pre
    while (cur?.next != null) {
        if(cur.next?.`val` == `val`){
            cur.next = cur.next?.next
        } else {
            cur = cur.next
        }

    }
    return pre.next
}

fun test206(){
    val l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(5)
    l1.next?.next?.next = ListNode(7)
    var ln = reverseList(l1)
    while (ln != null) {
        print(" ${ln.`val`} ")
        ln = ln.next
    }
}

fun test344(){
    val s = charArrayOf('h', 'e', 'l', 'l', 'o')
    reverseString(s)
    println(s.toList().toString())
}

/**
 * 206. 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 */
fun reverseList(head: ListNode?): ListNode? {
    var pre: ListNode? = null
    var cur = head
    while (cur != null) {
        val next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}

/**
 * 344.
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 示例 1：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 示例 2：
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
fun reverseString(s: CharArray) {
    var i = 0
    var j = s.size - 1
    while (i < j){
        val tmp = s[i]
        s[i] = s[j]
        s[j] = tmp
        i++
        j--
    }
}