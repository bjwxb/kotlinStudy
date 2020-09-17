package cn.wxb.kt.ext

import android.content.Context
import android.net.wifi.rtt.RangingRequest
import android.widget.ImageView
import cn.wxb.kt.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * 描述: ImageView 扩展
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/19 14:18
 */
fun ImageView.loadImage(context: Context, path: String, placeholder: Int = R.mipmap.ic_launcher, useCache: Boolean =false) {
    val options = getOptions(placeholder, useCache)
    Glide.with(context).load(path).apply(options).into(this)
}

/**
 * 加载圆形图片
 */
fun ImageView.loadCircleImage(context: Context, path: String, placeholder: Int = R.mipmap.ic_launcher, useCache: Boolean = false) {
    val options = getOptions(placeholder, useCache).circleCrop()
    Glide.with(context).load(path).apply(options).into(this)
}

/**
 * 加载圆角图片
 */
fun ImageView.loadRoundCornerImage(context: Context, path: String, roundingRadius: Int = 32, placeholder: Int = R.mipmap.ic_launcher, useCache: Boolean = false) {
    val options = getOptions(placeholder, useCache)
    Glide.with(context).load(path).apply(RequestOptions.bitmapTransform(RoundedCorners(roundingRadius))).apply(options).into(this)
}

fun getOptions(placeholder: Int, useCache: Boolean):RequestOptions{
    return RequestOptions().placeholder(placeholder)
        .skipMemoryCache(useCache)
}