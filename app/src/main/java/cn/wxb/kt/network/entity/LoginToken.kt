package cn.wxb.kt.network.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/4/30 17:08
 */
@Entity(tableName = "LoginToken")
data class LoginToken(
    val accessToken: String,
    val wcl: List<Wcl>,
    val yxRegister: YxRegister
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

data class Wcl(
    val ns: Ns,
    val roles: List<Role>,
    val wcId: String
)

data class YxRegister(
    val accid: String,
    val token: String
)

data class Ns(
    val id: String
)

data class Role(
    val id: String,
    val interval: Interval,
    val labels: List<Any>,
    val rsConfig: RsConfig,
    val subject: Subject
)

data class Interval(
    val start: Long
)

data class RsConfig(
    val account: String,
    val clientId: String,
    val rscId: String,
    val status: Int,
    val swOwnerPricing: Boolean,
    val swOwnerWm: Boolean
)

data class Subject(
    val age: Int,
    val bmi: String,
    val id: String,
    val uuCode: String
)


