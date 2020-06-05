package cn.wxb.kt.network.entity

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 11:45
 */
 data class PatientCroBean(
    val result:String?,
    val projectInfoList: List<ProjectInfo>?
)

data class ProjectInfo(
    val auditOrganizations: List<String>?,
    val duration: Int?,
    val endAt: Long?,
    val lowerDoctorName: Any?,
    val medicines: List<String>?,
    val operationAt: Any?,
    val `operator`: Any?,
    val organizationName: String?,
    val predictEndAt: Long?,
    val projectDay: Int?,
    val projectId: Int?,
    val projectName: String?,
    val reason: Any?,
    val relationId: String?,
    val specialOver: String?,
    val startAt: Long?,
    val trialStatus: String?,
    val upperDoctorName: String?
)