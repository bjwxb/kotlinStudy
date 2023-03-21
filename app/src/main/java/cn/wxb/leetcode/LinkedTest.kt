package cn.wxb.leetcode

import java.util.*

/**
 * @desc: 链表相关
 * 面试题 02.07. 链表相交
 * 24. 两两交换链表中的节点
 * 142. 环形链表 II
 *
 * @author: wuxiaobo
 * @date: 2023/3/18 11:41
 */
class LinkedTest {
}

fun main() {
//    test0207()
//    test24()
    test142()
//    test203()
//    test206()
}

fun test24(){
    val headA = ListNode(1)
    headA.next = ListNode(2)
    headA.next!!.next = ListNode(3)
    headA.next!!.next!!.next = ListNode(4)
//    var ret = swapPairs(headA)
    var ret = swapPairs2(headA)
    while (ret != null){
        print("${ret.value} ")
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
    print("${ret?.value} ")
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
        print("${ret.value} ")
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