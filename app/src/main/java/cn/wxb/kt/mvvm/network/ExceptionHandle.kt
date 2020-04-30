package cn.wxb.kt.mvvm.network

import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLException

/**
 * 描述:异常处理
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 13:44
 */
object ExceptionHandle {
    fun handleException(e: Throwable):ResponseThrowable{
        val ex: ResponseThrowable
        if (e is HttpException){
            ex = ResponseThrowable(Error.HTTP_ERROR, e)
        } else if (e is JsonParseException ||
                e is JSONException ||
                e is ParseException ||
                 e is MalformedJsonException){
            ex = ResponseThrowable(Error.PARSE_ERROR, e)
        } else if (e is ConnectException){
            ex = ResponseThrowable(Error.NETWORD_ERROR, e)
        } else if (e is SSLException){
            ex = ResponseThrowable(Error.SSL_ERROR, e)
        } else if (e is SocketTimeoutException){
            ex = ResponseThrowable(Error.TIMEOUT_ERROR, e)
        } else if (e is UnknownHostException){
            ex = ResponseThrowable(Error.TIMEOUT_ERROR, e)
        } else {
            ex = if (!e.message.isNullOrEmpty()){
                ResponseThrowable(e.message!!, e)
            } else {
                ResponseThrowable(Error.UNKNOWN, e)
            }
        }
        return ex
    }
}