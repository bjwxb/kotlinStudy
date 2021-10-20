package cn.wxb.kt.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.blankj.utilcode.util.LogUtils;

import org.jetbrains.annotations.NotNull;

/**
 * 描述:JobIntentService用于执行加入到队列中的任务。对Android 8.0及以上的系统，
 * JobIntentService的任务将被分发到JobScheduler.enqueue执行，
 * 对于8.0以下的系统，任务仍旧会使用Context.startService执行
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2021/10/15 4:04 下午
 */
public class TestService extends JobIntentService {
    public static final int JOB_ID = 1;

    public int time = 1;
    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, TestService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull @NotNull Intent intent) {
        time++;
//        IntentService
        LogUtils.e(time + ">>>>>>> " + Thread.currentThread().getName());
    }
}
