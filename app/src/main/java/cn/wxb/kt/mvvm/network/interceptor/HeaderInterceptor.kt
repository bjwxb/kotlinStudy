package cn.wxb.kt.mvvm.network.interceptor

import cn.wxb.kt.data.db.XzlDatabase
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/8 10:08
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        if (original.url().toString().contains("v0/oauth/token")) {
            val request = original.newBuilder()
                    //Basic ZG9jdG9yX3dlYjo= 醫生端
                .addHeader("Authorization", "Basic cGF0aWVudF9hcHA6")
                .build()
            return chain.proceed(request)
        } else if (original.url().toString().contains("v0/")) {
            val loginTokenDao =  XzlDatabase.getInstance().loginTokenLocalData()

            val request = original.newBuilder()
                .addHeader("Connection", "Keep-Alive")
                .addHeader("relationRef", "")
                .addHeader(
                    "Authorization",
                    "Bearer " + loginTokenDao.getAccessToken().access_token
                )
                .build()
            return chain.proceed(request)
        }
        return chain.proceed(original)
    }
}