package cn.wxb.kt.ui.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.wxb.kt.data.http.MineNetwork
import cn.wxb.kt.data.repo.MineRepository
import cn.wxb.kt.mvvm.base.BaseViewModel
import cn.wxb.kt.network.entity.OrderApiInfo
import cn.wxb.kt.network.entity.OrderBean
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/3 15:37
 */
class MineOrderViewModel :BaseViewModel(){
    private val loginRepository by lazy {
        MineRepository.getInstance(
            MineNetwork.getInstance()
        )
    }

    val mOrderBean = MutableLiveData<OrderBean>()

    fun getOrderBean(map:HashMap<String, String>) {
        launchGo({
            mOrderBean.value = loginRepository.getOrderList(map).data
        })
    }
}