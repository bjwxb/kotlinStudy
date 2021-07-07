package cn.wxb.kt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

/**
 * 描述:
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/22 10:39
 */
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float lastX, lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        LogUtils.e("x = " + event.getX() + " , y = " + event.getY()
           + "  , rawX = " + event.getRawX() + " , rawY = " + event.getRawY() + " " +
                ">> getTranslationX = "  +  getTranslationX() + " , getTranslationY = " +getTranslationY());
        int action = event.getAction();
        float x = event.getX(), y = event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int cx = (int)(x-lastX);
                int cy = (int)(y-lastY);
                LogUtils.e( x + ">>>>> lastX = " + lastX + " , >> lastY = " +lastY);
                layout(getLeft()+cx, getTop()+cy, getRight() + cx, getBottom() + cy);

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
