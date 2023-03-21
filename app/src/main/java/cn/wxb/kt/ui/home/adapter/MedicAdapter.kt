package cn.wxb.kt.ui.home.adapter

import cn.wxb.kt.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/3/14 17:11
 */
class MedicAdapter(list: MutableList<String>, layoutId: Int = R.layout.rv_item_medical) : BaseQuickAdapter<String, BaseViewHolder>(layoutId, list) {
    override fun convert(holder: BaseViewHolder, item: String) {

    }
}