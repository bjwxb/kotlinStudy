package cn.wxb.kt.util

import cn.wxb.kt.common.Constant.BASE_URL
import cn.wxb.kt.mvvm.network.interceptor.HeaderInterceptor
import cn.wxb.kt.mvvm.network.interceptor.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 10:16
 */
class RetrofitClient {

    companion object {
        fun getInstance() = SingleHolder.INSTANCE
        private lateinit var retrofit: Retrofit
    }

    private object SingleHolder{
        val INSTANCE = RetrofitClient()
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient():OkHttpClient{
        return  OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
//            .addInterceptor( LoggingInterceptor("OkHttp")
////        loggingInterceptor.setLevel(LoggingInterceptor.Level.BODY)
////        //log颜色级别，决定了log在控制台显示的颜色
////        loggingInterceptor.setColorLevel(Level.WARNING))
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(LoggingInterceptor("okHttp").apply {
                setLevel(LoggingInterceptor.Level.BODY)
                setColorLevel(Level.WARNING)
            })
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
            .build()
    }

    fun <T> create(service: Class<T>?): T =
        retrofit.create(service!!) ?: throw RuntimeException("Api service is null!")

}

fun <T> Observable<T>.dispatchDefault(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
