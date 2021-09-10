package cn.wxb.kt.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.wxb.kt.ui.home.fragment.*

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/2 11:33
 */
class VpMainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    private var mFragmentList: List<Fragment> = listOf(
        HomeFragment.newInstance(),
        BlankFragment.newInstance(),
        HealthFragment.newInstance(),
        MineFragment.newInstance()
    )

    override fun getItemCount() = mFragmentList.size

    override fun createFragment(position: Int) = mFragmentList.get(position)

}