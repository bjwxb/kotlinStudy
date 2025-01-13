import java.io.BufferedWriter
import java.io.File

fun main() {
    // 输入字符串数组
    //describe，require，consider，mind，cliff，option，must，arrest，stable，gas，equip，tent
    val input =
        arrayOf("describe", "require", "consider", "mind", "cliff", "option")


    // 调用方法生成排列并写入文件
    writePermutationsToFile(input)

    println("所有排列已写入文件")
}

var fileIndex = 1 // 文件索引
const val maxFileSize = 20 * 1024 * 1024 // 5 MB

/**
 * 将字符串数组的所有排列写入文件
 * @param input 字符串数组
 */
fun writePermutationsToFile(input: Array<String>) {


    // 调用递归方法生成排列并写入文件
    generatePermutations(input, 0)

    writer.close() // 确保在结束时关闭文件
}

/**
 * 递归生成字符串数组的所有排列
 * @param arr 字符串数组
 * @param index 当前排列的起始索引
 * @param writer 用于写入文件的 BufferedWriter
 */
var filePath = "${System.getProperty("user.dir")}/$fileIndex-permutations.txt"
var writer = File(filePath).bufferedWriter(Charsets.UTF_8)
fun generatePermutations(arr: Array<String>, index: Int) {
    if (index == arr.size - 1) {
        // 将当前排列写入文件
        writer.write(arr.joinToString(","))
        writer.newLine() // 换行

        // 检查文件大小
        if (File("${System.getProperty("user.dir")}/$fileIndex-permutations.txt").length() >= maxFileSize) {
            writer.close() // 关闭当前文件
            fileIndex++ // 增加文件索引
            val newFilePath = "${System.getProperty("user.dir")}/$fileIndex-permutations.txt"
            writer = File(newFilePath).bufferedWriter() // 创建新文件
        }

        return
    }

    for (i in index until arr.size) {
        // 交换元素以生成新的排列
        swap(arr, index, i)
        // 递归生成子排列
        generatePermutations(arr, index + 1)
        // 回溯：恢复原始数组顺序
        swap(arr, index, i)
    }
}

/**
 * 交换数组中的两个元素
 * @param arr 字符串数组
 * @param i 索引 i
 * @param j 索引 j
 */
fun swap(arr: Array<String>, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}