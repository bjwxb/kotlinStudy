package cn.wxb.leetcode

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2023/7/18 11:46
 */
class TVideo {
}

fun main() {

    test()
}

fun test(){
    val timeList = mutableListOf<Long>().apply {
//        0, 8333333, 14800000, 19600000, 27933333
        add(0)
        add(8333333)
        add(14800000)
        add(19600000)
        add(27933333)
    }

    for(i in 0..30){
        val t = i * 1000 * 1000L
        val ret = getCurrTime(t, timeList)
        println("$i---- $ret")
        val ret2 = getIndex(t, timeList);
        println("$i >>>> $ret2")
    }
}

private fun getIndex(time: Long, list: List<Long>):Long{
    var left = 0
    var right = list.size - 1
    while(left <= right){
        val mid = left + (right - left)/2
        val num = list[mid]
        if(num == time) {
            return time
        } else if (num > time) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    if(left == 0){
        return list.get(0)
    }

    if(left > 0 && left < list.size){
        val next = list[left]
        if(next == time){
            return next
        }
        val pre = list[left - 1]
        val diff = (next - pre)/2

        println(">> time = $time, pre = $pre, next = $next, diff = $diff")
        if(time < pre + diff){
            return pre
        } else {
            return next
        }

    } else {
        return list.last()
    }
}

private fun getCurrTime(time: Long, list: List<Long>): Long {
    if (time == 0L) {
        return time
    }
    var ret = time
    for (i in 1 until list.size) {
        val curr = list[i]
        if (time == curr) {
            ret = time
            break
        } else if (time < curr) {
            val pre = list[i - 1]
            val diff = (curr - pre) / 2
            if (time > pre) {
                ret = if (time < pre + diff) {
                    pre
                } else {
                    curr
                }
                break
            }
        } else {
            ret = if (i + 1 <= list.size - 1) {
                val next = list[i + 1]//next = 19 // .6
                if(next < time){
                    continue
                }
                val diff = (next - curr) / 2//diff = 2.4
                if (time < curr + diff) {//time = 17, 18
                    curr
                } else {
                    next
                }
            } else {
                curr
            }
            break
        }
    }
    return ret
}