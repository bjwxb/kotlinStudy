package cn.wxb.kt.app.base

import android.view.View
import androidx.databinding.ViewDataBinding
import cn.wxb.kt.R
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 15:13
 */
@Suppress("UNCHECKED_CAST")
class BaseDBViewHoder<BD : ViewDataBinding>(val view: View) : BaseViewHolder(view) {

    fun getDataBinding(): BD {
        return view.getTag(R.id.BaseQuickAdapter_databinding_support) as BD
    }

}