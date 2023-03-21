package cn.wxb.kt.ui.home.adapter

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import cn.wxb.kt.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import  cn.wxb.kt.widget.CircleProgressBar

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 10:23
 */
class VpMainRvAdapter(list: MutableList<String>, layoutId: Int = R.layout.rv_item_vp_main) : BaseQuickAdapter<String, BaseViewHolder>(layoutId, list){


    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tvContent, item)

        val progress = holder.getView<CircleProgressBar>(R.id.progress)
//        with(progress) {
//            rindColorArray = intArrayOf(
//                Color.parseColor("#0888FF"),
//                Color.parseColor("#6CD0FF")
//            )
//            startAnim(85)
//        }
        progress.startAnim(25)

    }

}