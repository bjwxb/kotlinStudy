package cn.wxb.kt.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 11:40
 */
@Entity(tableName = "DoctorBean")
data class DoctorBean(
    val userId:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}