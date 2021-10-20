package cn.wxb.kt.ui.home.viewmodel;

import androidx.lifecycle.LiveData;

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2021/10/13 9:43 上午
 */
public class MainLiveData extends LiveData<String> {

    @Override
    protected void onActive() {
        super.onActive();
        //add listener
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        //remove listener
    }
}
