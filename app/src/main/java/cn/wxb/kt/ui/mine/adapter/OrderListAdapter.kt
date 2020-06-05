package cn.wxb.kt.ui.mine.adapter

import cn.wxb.kt.R
import cn.wxb.kt.network.entity.OrderApiInfo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 15:51
 */
class OrderListAdapter(list: MutableList<OrderApiInfo>) :
    BaseQuickAdapter<OrderApiInfo, BaseViewHolder>(R.layout.rv_item_order, list), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: OrderApiInfo) {
        holder.setText(R.id.tvContent, item.goodsName)
            .setText(R.id.tv_price, item.orderNo)
    }



}