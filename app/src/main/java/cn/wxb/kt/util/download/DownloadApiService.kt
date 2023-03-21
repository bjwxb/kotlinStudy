package cn.wxb.kt.util.download

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/4/5 15:02
 */
interface DownloadApiService {

    @Streaming
    @GET
    suspend fun downloadFile(@Url url:String?):ResponseBody
}