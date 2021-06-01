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
    var accessToken : String,
    var refresh_token : String,
    var uid : String,
    var error_description : String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}