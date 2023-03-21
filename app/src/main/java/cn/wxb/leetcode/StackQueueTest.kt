package cn.wxb.leetcode

import java.io.IOException
import java.lang.NullPointerException
import java.util.*
import kotlin.jvm.Throws

/**
 * @desc:
 *      225. 用队列实现栈
 *      232. 用栈实现队列
 * @author: wuxiaobo
 * @date: 2023/3/9 15:43
 */
class StackQueueTest {
}

fun main(){
    test225()
//    test232()
}

fun test225(){
    val stack = MyStack()
    stack.push(1)
    stack.push(2)
    println(stack.top())
    stack.push(3)
    println(stack.top())
    println(stack.empty())

}

fun test232(){
    val queue = MyQueue()
    queue.push(1)
    queue.push(2)
    println(queue.peek())
    println(queue.pop())
    println(queue.empty())
}

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是push to back、peek/pop from front、size 和is empty这些操作。
 * 你所使用的语言也许不支持队列。你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 */
class MyStack() {
    val queueIn = LinkedList<Int>()
    val queueOut = LinkedList<Int>()

    fun push(x: Int) {
        queueIn.add(x)
    }

    fun pop(): Int {
        while (!queueIn.isEmpty()){
            queueOut.addFirst(queueIn.first)
            queueIn.removeFirst()
        }
        if(queueOut.isEmpty()){
            return -1
        }
        val first = queueOut.first
        queueOut.removeFirst()
        return first
    }

    fun top(): Int {
        while (!queueIn.isEmpty()){
            queueOut.addFirst(queueIn.first)
            queueIn.removeFirst()
        }
        if(queueOut.isEmpty()){
            return -1
        }
        return queueOut.first
    }

    fun empty(): Boolean {
        return queueOut.isEmpty() && queueIn.isEmpty()
    }
}

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 */
class MyQueue() {
    val sIn: Stack<Int> = Stack()
    val sOut: Stack<Int> = Stack()

    fun push(x: Int) {
        sIn.push(x)
    }

    fun pop(): Int {
        if(sOut.isEmpty()){
            while(sIn.isNotEmpty()){
                sOut.push(sIn.pop())
            }
        }
        return sOut.pop()
    }

    fun peek(): Int {
        if(sOut.isEmpty()){
            while(sIn.isNotEmpty()){
                sOut.push(sIn.pop())
            }
        }
        return sOut.peek()
    }

    fun empty(): Boolean {
        return sIn.isEmpty() && sOut.isEmpty()
    }

}
