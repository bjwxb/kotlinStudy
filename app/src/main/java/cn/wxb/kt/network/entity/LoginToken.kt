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
    @ColumnInfo(name = "access_token")
    var access_token : String,
    var token_type : String,
    @ColumnInfo(name = "refresh_token")
    var refresh_token : String,
    var scope : String,
    @ColumnInfo(name = "uid")
    var uid : String,
    var error : String,
    var error_description : String,
    var status : String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}