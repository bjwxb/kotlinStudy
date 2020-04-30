package cn.wxb.kt.mvvm.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 14:21
 */
object ImageAdapter {
    @BindingAdapter(value = ["url", "placeholder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String, placeholder: Int) {
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions().placeholder(placeholder))
                .into(imageView)
    }
}