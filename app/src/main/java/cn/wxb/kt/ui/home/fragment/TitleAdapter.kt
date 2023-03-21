package cn.wxb.kt.ui.home.fragment

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import cn.wxb.kt.R
/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/4/2 19:46
 */
class TitleAdapter(list:MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_title, list) {

    override fun convert(holder: BaseViewHolder, item: String) {
    }
}

class ContentAdapter(list:MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_content, list) {

    override fun convert(holder: BaseViewHolder, item: String) {
    }
}

class FooterAdapter(list:MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_footer, list) {

    override fun convert(holder: BaseViewHolder, item: String) {
    }
}