package cn.wxb.kt.ext

import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_v2.*

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/19 14:09
 */

//TabLayout扩展方法--绑定ViewPager2
fun TabLayout.setUpWithViewPager2(vp:ViewPager2, menus:List<String>){
    TabLayoutMediator(this, vp,
        TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
            run {
                tab.text = menus.get(position)
            }
        }
    ).attach()
}