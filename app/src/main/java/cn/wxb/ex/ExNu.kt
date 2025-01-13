//import java.io.BufferedWriter
//import java.io.File
//
//fun main() {
//    // 输入字符串数组
//// val input = arrayOf("a", "b", "c", "d")
//    val input =
//        arrayOf("mercy", "clarify", "caution", "dwarf", "start", "giant", "quit", "equal", "fiscal", "pink", "estate", "rival")
//
//    // 调用方法生成排列并写入文件
//    writePermutationsToFile(input)
//
//    println("所有排列已写入文")
//}
//
///**
// * 将字符串数组的所有排列写入文件
// * @param input 字符串数组
// * @param filePath 输出文件路径
// */
//var i = 0
//var k = 1
//fun writePermutationsToFile(input: Array<String>) {
//    // 输出文件路径
//    val filePath = "${System.getProperty("user.dir")}/$k-permutations.txt"
//
//    // 打开文件进行写入
//    File(filePath).bufferedWriter().use { writer ->
//        // 调用递归方法生成排列并写入文件
//        generatePermutations(input, 0, writer)
//    }
//}
//
///**
// * 递归生成字符串数组的所有排列
// * @param arr 字符串数组
// * @param index 当前排列的起始索引
// * @param writer 用于写入文件的 BufferedWriter
// */
//fun generatePermutations(arr: Array<String>, index: Int, writer: BufferedWriter) {
//    if (index == arr.size - 1) {
//        // 将当前排列写入文件
//        writer.write(arr.joinToString(","))
//        writer.newLine() // 换行
//        return
//    }
//
//    for (i in index until arr.size) {
//        // 交换元素以生成新的排列
//        swap(arr, index, i)
//        // 递归生成子排列
//        generatePermutations(arr, index + 1, writer)
//        // 回溯：恢复原始数组顺序
//        swap(arr, index, i)
//    }
//}
//
///**
// * 交换数组中的两个元素
// * @param arr 字符串数组
// * @param i 索引 i
// * @param j 索引 j
// */
//fun swap(arr: Array<String>, i: Int, j: Int) {
//    val temp = arr[i]
//    arr[i] = arr[j]
//    arr[j] = temp
//}