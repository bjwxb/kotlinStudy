package cn.wxb.kt.util

import cn.wxb.kt.data.db.XzlDatabase
import cn.wxb.kt.data.http.LoginNetwork
import cn.wxb.kt.data.repo.LoginRepository

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/5/6 13:37
 */
object InjectorUtil {

    fun getLoginRepository() = LoginRepository.getInstance(
        LoginNetwork.getInstance(),
        XzlDatabase.getInstance().loginTokenLocalData(), //token本地存储
        XzlDatabase.getInstance().doctorBeanLocalData()//医生信息本地存储
    )
}