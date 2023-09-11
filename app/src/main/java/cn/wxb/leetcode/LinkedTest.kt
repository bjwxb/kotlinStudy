package cn.wxb.leetcode

import java.util.*

/**
 * @desc: 链表相关
 * 面试题 02.07. 链表相交
 * 21. 合并两个有序链表
 * 24. 两两交换链表中的节点
 * 83. 删除排序链表中的重复元素
 * 142. 环形链表 II
 *
 * @author: wuxiaobo
 * @date: 2023/3/18 11:41
 */
class LinkedTest {
}

fun main() {
//    test0207()
//    test21()
//    test24()
    test82()
//    test83()
//    test142()
    test203()
//    test206()
}

fun test21(){
    val headA = ListNode(1)
    headA.next = ListNode(2)
    headA.next!!.next = ListNode(4)

    val headB = ListNode(1)
    headB.next = ListNode(3)
    headB.next!!.next = ListNode(4)

    val ret = mergeTwoLists(headA, headB)
    print(ret)
}

fun test24(){
    val headA = ListNode(1)
    headA.next = ListNode(2)
    headA.next!!.next = ListNode(3)
    headA.next!!.next!!.next = ListNode(4)
//    var ret = swapPairs(headA)
    var ret = swapPairs2(headA)
    while (ret != null){
        print("${ret.`val`} ")
        ret = ret.next
    }
}

fun test82(){
    val headA = ListNode(1)
    headA.next = ListNode(1)
    headA.next!!.next = ListNode(1)
    headA.next!!.next!!.next = ListNode(3)
    headA.next!!.next!!.next!!.next = ListNode(5)
    var ret = deleteDuplicates82(headA)
    while(ret != null){
        print("${ret.`val`} ")
        ret = ret.next
    }
}

fun test83(){
    val headA = ListNode(1)
    headA.next = ListNode(1)
    headA.next!!.next = ListNode(2)
    headA.next!!.next!!.next = ListNode(4)
    headA.next!!.next!!.next!!.next = ListNode(4)
    var ret = deleteDuplicates(headA)
    while(ret != null){
        print("${ret.`val`} ")
        ret = ret.next
    }
}


fun test142(){
    val head = ListNode(1)
    val cyc = ListNode(2)
    head.next = cyc
    cyc.next = ListNode(3)
    cyc.next?.next = ListNode(4)
    cyc.next?.next?.next = ListNode(5)
    cyc.next?.next?.next?.next = ListNode(6)
    cyc.next?.next?.next?.next?.next = cyc

    val ret = detectCycle(head)
    print("${ret?.`val`} ")
}

fun test0207(){
    val headA = ListNode(4)
    headA.next = ListNode(1)
    headA.next!!.next = ListNode(8)
    headA.next!!.next!!.next = ListNode(4)
    headA.next!!.next!!.next!!.next = ListNode(5)
    val headB = ListNode(5)
    headB.next = ListNode(0)
    headB.next!!.next = ListNode(1)
    headB.next!!.next!!.next = headA.next

    var ret = getIntersectionNode(headA, headB)
    while(ret!=null){
        print("${ret.`val`} ")
        ret = ret.next
    }
}

/** 142 start **/
/**
 * 142. 环形链表 II
 * 给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 *
 */
fun detectCycle(head: ListNode?): ListNode? {
    var slow = head
    var fast = head
    while (true){
        //如果fast==null||fast.next==null，则说明没有环，返回null
        if(fast?.next == null){
            return null
        }
        slow = slow?.next
        fast = fast.next!!.next
        if(slow == fast){
            break
        }
    }
    fast = head
    while(fast != slow){
        slow = slow!!.next
        fast = fast!!.next
    }

    return fast
}
/** 142 end **/

/** 0207 start **/
/**
 * 0207
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * @param headA ListNode?
 * @param headB ListNode?
 * @return ListNode?
 */
fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    if(headA == null || headB == null){
        return null
    }

    var ha = headA
    var hb = headB
    while (ha != hb){
        ha = if(ha == null){
            headB
        } else {
            ha.next
        }

        hb = if(hb == null){
            headA
        } else {
            hb.next
        }
    }
    return ha
}
/** 0207 end **/

/** 21 start **/
/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @param list1 ListNode?
 * @param list2 ListNode?
 * @return ListNode?
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if(null == list1 && null == list2){
        return null
    }
    var l1 = list1
    var l2 = list2
    val dummy = ListNode(-1)
    var tmp:ListNode? = dummy
    while(l1 != null && l2 != null){
        val n1 = l1.`val`
        val n2 = l2.`val`
        if(n1 < n2){
            tmp?.next = ListNode(n1)
            l1 = l1.next
        } else {
            tmp?.next = ListNode(n2)
            l2 = l2.next
        }
        tmp = tmp?.next
    }
    tmp?.next = l1 ?: l2

    return dummy.next
}
/** 21 end **/

/** 24 start **/
/**
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 输入：head = [1,2,3,4]  输出：[2,1,4,3]
 * 输入：head = []  输出：[]
 * 输入：head = [1]  输出：[1]
 *
 *
 * @param head ListNode?
 * @return ListNode?
 */
fun swapPairs(head: ListNode?): ListNode? {
    val dummy = ListNode(-1)
    dummy.next = head
    var pre:ListNode? = dummy
    var cur = head
    val stack = Stack<ListNode>()
    while (cur?.next != null){
        stack.push(cur)
        stack.push(cur.next)
        cur = cur.next!!.next
        pre?.next = stack.pop()
        pre = pre?.next
        pre?.next = stack.pop()
        pre = pre?.next
    }
    pre?.next = cur

    return dummy.next
}

fun swapPairs2(head: ListNode?): ListNode? {
    if(head?.next == null){
        return head
    }

    val next = head.next
    head.next = swapPairs2(next?.next)
    next?.next = head

    return next
}
/** 24 end **/

/** 82 start **/
/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * 输入：head = [1,2,3,3,4,4,5]   输出：[1,2,5]
 * 输入：head = [1,1,1,2,3]  输出：[2,3]
 * @param head ListNode?
 * @return ListNode?
 */
fun deleteDuplicates82(head: ListNode?): ListNode? {
    if(null == head){
        return null
    }
    val dummy = ListNode(-1)
    var cur:ListNode? = dummy
    dummy.next = head
    while(cur?.next != null && cur.next?.next != null){
        //如果后两个节点值相同，则将当前节点的next指向重复节点的下一个节点
        if(cur.next!!.`val` == cur.next!!.next!!.`val`){
            val x = cur.next!!.`val`
            while(cur.next != null && x == cur.next!!.`val`){
                cur.next = cur.next?.next
            }
        } else {
            cur = cur.next
        }
    }

    return dummy.next
}
/** 82 end **/

/** 83 start **/
/**
 * 83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 输入：head = [1,1,2]  输出：[1,2]
 * 输入：head = [1,1,2,3,3]  输出：[1,2,3]
 */
fun deleteDuplicates(head: ListNode?): ListNode? {
    if(head == null){
        return null
    }
    var curr = head
    while (curr != null){
        if(curr.`val` == curr.next?.`val`){
            curr.next = curr.next?.next
        } else {
            curr = curr.next
        }
    }
    return head
}
/** 83 end **/