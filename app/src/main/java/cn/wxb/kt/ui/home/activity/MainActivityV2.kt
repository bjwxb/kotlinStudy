package cn.wxb.kt.ui.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cn.wxb.kt.R
import cn.wxb.kt.ext.setUpWithViewPager2
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.mvvm.base.NoViewModel
import cn.wxb.kt.ui.home.adapter.VpMainAdapter
import cn.wxb.kt.ui.home.fragment.HealthFragment
import cn.wxb.kt.ui.home.fragment.HomeFragment
import cn.wxb.kt.ui.home.fragment.MedicFragment
import cn.wxb.kt.ui.home.fragment.MineFragment
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_v2.*

/**
 * 描述: mainActivity(viewPage2 + fragment + tabLayout)
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/2 11:26
 */
class MainActivityV2 : BaseActivity<MainViewModel, ViewDataBinding>() {

    private var mMenuList: List<String> = listOf(
        "首页", "服药", "健康", "我的"
    )

    private var mIconList: List<Int> = listOf(
        R.drawable.selector_tab_chat_icon,
        R.drawable.selector_tab_medicine_icon,
        R.drawable.selector_tab_health_icon,
        R.drawable.selector_tab_user_icon
    )

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, MainActivityV2::class.java))
        }
    }

    override fun layoutId() = R.layout.activity_main_v2

    override fun initView(savedInstanceState: Bundle?) {
        initVp();
    }

    private fun initVp() {
        vpContent.adapter = VpMainAdapter(this)
        //禁止滚动true为可以滑动false为禁止
        vpContent.isUserInputEnabled = false
        //设置垂直滚动ORIENTATION_VERTICAL，横向的为
        vpContent.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
        //切换到指定页，是否展示过渡中间页
//        vpContent.setCurrentItem(0, false)
        //设置一个缩放动画
//        vpContent.setPageTransformer(mAnimator);

        vpContent.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                LogUtils.e(">>>>>>>>>>> position = " + position)
            }
        })

        //tabLayout bind viewPager2,通过扩展方法实现
        tlMenu.setUpWithViewPager2(vpContent, mMenuList)
    }

    override fun initData() {
        viewModel.getPatientInfo()
    }
}
