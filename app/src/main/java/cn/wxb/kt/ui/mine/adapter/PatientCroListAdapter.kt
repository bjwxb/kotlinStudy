package cn.wxb.kt.ui.mine.adapter

import android.util.TimeUtils
import cn.wxb.kt.R
import cn.wxb.kt.network.entity.ProjectInfo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import java.lang.StringBuilder

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 14:08
 */
class PatientCroListAdapter(list: MutableList<ProjectInfo>)
    : BaseQuickAdapter<ProjectInfo, BaseViewHolder>(R.layout.rv_item_patient_cro_list,  list) {

    override fun convert(holder: BaseViewHolder, item: ProjectInfo) {
        holder.setText(R.id.tv_cro_title, item.projectName)
            .setText(R.id.tv_cro_organ, "机构: " + item.organizationName) //机构名称
            .setText(R.id.tv_cro_doctor, "医生: ${item.upperDoctorName}")

        val list = item.auditOrganizations
        list?.let {
            val sb = StringBuilder()
            for (organ in it){
                sb.append(organ).append(",")
            }
            sb.deleteCharAt(sb.length - 1)
            holder.setText(R.id.tv_cro_verify_organ, "认可检查机构: ${sb}")
        }

    }

}