package cn.wxb.kt.util.download

import RetrofitClient
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import cn.wxb.kt.util.download.FileUtil
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import cn.wxb.kt.App
import kotlinx.coroutines.withContext


/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/4/5 15:01
 */
class DownloadUtil {

    companion object {
        fun getInstance() = SingleHolder.INSTANCE
    }

    private object SingleHolder {
        val INSTANCE = DownloadUtil()
    }

    private val apiService by lazy {
        RetrofitClient.getInstance().create(DownloadApiService::class.java)
    }

    fun downloadFile(url: String?, block:()->Unit = {}) {
        if (url.isNullOrBlank()) {
            return
        }
//        val file = File("${Environment.getExternalStorageDirectory().path}/download/${url.substringAfterLast("/")}")
        val suffix: String = url.substringAfterLast(".")
//        val file = File("${Environment.getExternalStorageDirectory().path}/download/${System.currentTimeMillis()}.$suffix")

        val dir = "${Environment.getExternalStorageDirectory().path}/sl/image}"
        val state = Environment.getExternalStorageState()


        LogUtils.e("$state>>> dir = $dir")


        val fileDir = File(dir)
        var ret = false
        if (!fileDir.exists()) {
            ret = fileDir.mkdirs()
        }

        val file = if (ret) {
            File("$dir${System.currentTimeMillis()}.$suffix")
        } else {
            if(suffix == "mp4"){
                File("${Environment.getExternalStorageDirectory().path}/DCIM/camera/${System.currentTimeMillis()}.$suffix")
            } else {
                File("${Environment.getExternalStorageDirectory().path}/download/${System.currentTimeMillis()}.$suffix")
            }
        }

        if (!file.exists()) {
            file.createNewFile()
        }


        GlobalScope.launch {
            kotlin.runCatching {
                val response = apiService.downloadFile(url)
                val stream = response.byteStream()
                val fos = FileOutputStream(file)
                fos.write(stream.readBytes())
                //缓冲区
//                val buff = ByteArray(1024)
//                var len = 0
//                while ((stream.read(buff).also { len = it }) != -1) {
//                    fos.write(buff, 0, len)
//                }
                LogUtils.e(">>>> ${file.absolutePath}")
                fos.flush()
                fos.close()
                if (suffix == "mp4") {
                    FileUtil.saveVideo(file)
                } else {
                    FileUtil.saveImage(file)
                }
                withContext(Dispatchers.Main){
                    ToastUtils.showLong("下载成功 ${file.absolutePath}")
                }
            }.getOrElse {
                it.printStackTrace()
                ToastUtils.showLong("下载失败")
            }.also {
                block.invoke()
                LogUtils.e("======= finally ==========")
            }

        }
    }
}

//fun getDownloadPath(context: Context?): String? {
//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//        //android 11
//        SyncStateContract.Constants.SDCardConstants.getDir(context).toString() + File.separator
//    } else {
//        Environment.getExternalStorageDirectory().toString() + "/winetalk/"
//    }
//}

//}