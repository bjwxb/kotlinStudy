package cn.wxb.kt.util.download

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import cn.wxb.kt.App
import com.blankj.utilcode.util.LogUtils
import java.io.File


/**
 * @desc:文件保存工具类
 * @author: wuxiaobo
 * @date: 2022/4/6 10:51
 */
object FileUtil {
    /**
     * 保存图片
     * @param context
     * @param file
     */
    fun saveImage(file: File) {
        val context = App.getInstance()
        val localContentResolver: ContentResolver = context.contentResolver
        val localContentValues = getImageContentValues(file, System.currentTimeMillis())
        localContentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValues)
        val localIntent = Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file))
        localIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.sendBroadcast(localIntent)
    }

    private fun getImageContentValues(paramFile: File, paramLong: Long): ContentValues {
        val localContentValues = ContentValues()
//        localContentValues.put("title", paramFile.getName())
//        localContentValues.put("_display_name", paramFile.getName())
//        localContentValues.put("mime_type", "image/jpeg")
//        localContentValues.put("datetaken", java.lang.Long.valueOf(paramLong))
//        localContentValues.put("date_modified", java.lang.Long.valueOf(paramLong))
//        localContentValues.put("date_added", java.lang.Long.valueOf(paramLong))
//        localContentValues.put("orientation", Integer.valueOf(0))
//        localContentValues.put("_data", paramFile.absolutePath)
//        localContentValues.put("_size", java.lang.Long.valueOf(paramFile.length()))

        localContentValues.put(MediaStore.Images.Media.TITLE, paramFile.name)
        localContentValues.put(MediaStore.Images.Media.DISPLAY_NAME, paramFile.name)
        localContentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        localContentValues.put(MediaStore.Images.Media.DATE_TAKEN, java.lang.Long.valueOf(paramLong))
        localContentValues.put(MediaStore.Images.Media.DATE_MODIFIED, paramLong)
        localContentValues.put(MediaStore.Images.Media.DATE_ADDED, java.lang.Long.valueOf(paramLong))
        localContentValues.put(MediaStore.Images.Media.ORIENTATION, Integer.valueOf(0))
        localContentValues.put(MediaStore.Images.Media.DATA, paramFile.absolutePath)
        localContentValues.put(MediaStore.Images.Media.SIZE, java.lang.Long.valueOf(paramFile.length()))

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            localContentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/img}")
        }

        return localContentValues
    }

    /**
     * 保存视频
     * @param context
     * @param file
     */
    fun saveVideo(file: File) {
//        val context = App.getInstance()
//        //是否添加到相册
//        val localContentResolver: ContentResolver = context.contentResolver
//        val localContentValues = getVideoContentValues(file, System.currentTimeMillis())
//        localContentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, localContentValues)
//
//        val localIntent = Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file))
//        localIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        context.sendBroadcast(localIntent)
        refreshApi29(file.absolutePath)
    }

    /**
     * 刷新视频到相册方法一 API通用方法包括API29及以上高版本方法
     *
     * @param activity     当前页面
     * @param fileNamePath 文件路径
     */
    private fun refreshApi29(fileNamePath: String) {
        if(fileNamePath.isBlank()){
            return
        }
        val context = App.getInstance()
        val mMediaScanner = MediaScannerConnection(context, null)
        mMediaScanner.connect()
        mMediaScanner.scanFile(fileNamePath, "video/mp4")
        //刷新相册，mineTypes为null的话让系统自己根据文件后缀判断文件类型
//        MediaScannerConnection.scanFile(context, arrayOf(fileNamePath), null) { path: String?, uri: Uri? ->
//            LogUtils.e("资源刷新成功路径为", path!!)
//        }
        //代表只刷新视频格式为mp4类型其它格式视频文件不刷新
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"video/mp4"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
        //代表刷新视频文件，只要是视频都刷新根据当前Android系统支持哪些视频格式进行刷新
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"video/*"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
        //代表只刷新图片格式为jpg的文件到相册中
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"image/jpg"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
        //代表刷新图片到相册只要是图片就会刷新
//                MediaScannerConnection.scanFile(activity, new String[]{fileNamePath}, new String[]{"image/*"}, (path, uri) -> Log.e("资源刷新成功路径为", path));
    }

    private fun getVideoContentValues(paramFile: File, paramLong: Long): ContentValues {
        val localContentValues = ContentValues()
        localContentValues.put(MediaStore.Video.Media.TITLE, paramFile.name)
        localContentValues.put(MediaStore.Video.Media.DATE_TAKEN, java.lang.Long.valueOf(paramLong))
        localContentValues.put(MediaStore.Video.Media.DATE_ADDED, java.lang.Long.valueOf(paramLong))
        localContentValues.put(MediaStore.Video.Media.SIZE, java.lang.Long.valueOf(paramFile.length()))
        localContentValues.put(MediaStore.Video.Media.DISPLAY_NAME, paramFile.name)
        localContentValues.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
        localContentValues.put(MediaStore.Video.Media.DATE_MODIFIED, paramLong)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            localContentValues.put(MediaStore.Video.Media.RELATIVE_PATH, "DCIM/camera")
        }

        return localContentValues
    }

    /**
     * 创建文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    fun makeFilePath(filePath: String, fileName: String): File? {
        var file: File? = null
        makeRootDirectory(filePath)
        try {
            file = File(filePath + fileName)
            if (!file.exists()) {
                file.createNewFile()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }

    /**
     * 创建文件夹
     *
     * @param filePath
     */
    fun makeRootDirectory(filePath: String?) {
        val file: File?
        try {
            file = File(filePath)
            if (!file.exists()) {
                file.mkdir()
            }
        } catch (e: Exception) {
            Log.i("error:", e.toString() + "")
        }
    }


    /**
     * 判断文件夹是否存在,如果不存在则创建文件夹
     *
     * @param path
     */
    fun isExist(path: String?): Boolean {
        val file = File(path)
        return file.exists()
    }
}

