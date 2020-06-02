package cn.wxb.kt.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/28 16:10
 */
@Entity(tableName = "PATIENT_INFO")
data class PatientInfo(
//    val advice: Advice?,
//    val assistant: Any?,
//    val doctor: Doctor?,
//    val plans: List<Plan>?,
//    val recordOperations: List<Any>?,
    val user: User?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

data class Advice(
    val content: Content?,
    val createdAt: Long?,
    val doctorInfo: DoctorInfo?,
    val id: String?,
    val `receiver`: String?,
    val sender: String?,
    val status: Any?
)

data class Doctor(
    val avatar: String?,
    val departmentId: String?,
    val departmentName: String?,
    val id: String?,
    val name: String?,
    val organizationId: String?,
    val organizationName: String?,
    val organizationShowId: String?,
    val showId: String?,
    val title: String?
)

data class Plan(
    val boxUuid: String?,
    val category: String?,
    val clinicalProjectId: Any?,
    val commodityName: String?,
    val count: Int?,
    val cycleDays: Int?,
    val dosage: Int?,
    val dosageFormUnit: String?,
    val dosageUnit: String?,
    val ended: Any?,
    val id: String?,
    val imageId: Any?,
    val ingredient: String?,
    val isUnknown: String?,
    val medicineHash: String?,
    val medicineId: String?,
    val medicineName: String?,
    val medicineVia: Int?,
    val planSeqWithBox: Any?,
    val positionNo: Int?,
    val remindFirstAt: Long?,
    val started: Long?,
    val takeAt: Int?,
    val zone: Int?
)

data class User(
    val address: String?,
    val area: String?,
    val avatar: String?,
    val bank: Any?,
    val bankCardNumber: Any?,
    val bindingDoctor: List<BindingDoctor>?,
    val birthday: Long?,
    val education: Any?,
    val ethnicity: String?,
    val expireTimeType: String?,
    val height: String?,
    val idCard: Any?,
    val idCardUrl: Any?,
    val marriage: Int?,
    val name: String?,
    val photo: List<Any>?,
    val qrCodeUrl: String?,
    val realName: Any?,
    val realStatus: Any?,
    val sex: Int?,
    val showId: Int?,
    val status: Int?,
    val tel: String?,
    val type: Int?,
    val waistline: String?,
    val wechatBindStatus: String?,
    val weight: String?
)

data class Content(
    val `data`: DataX?,
    val type: Int?
)

data class DoctorInfo(
    val avatar: String?,
    val departmentId: Any?,
    val departmentName: Any?,
    val id: String?,
    val name: String?,
    val organizationId: String?,
    val organizationName: Any?,
    val organizationShowId: Any?,
    val showId: Any?,
    val title: String?
)

data class DataX(
    val content: String?,
    val entrustment: Any?,
    val inspectionStandards: Any?,
    val medicineSupplement: Any?,
    val type: Int?
)

data class BindingDoctor(
    val doctorId: String?
)