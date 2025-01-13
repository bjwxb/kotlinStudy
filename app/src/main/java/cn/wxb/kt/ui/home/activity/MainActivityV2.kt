package cn.wxb.kt.ui.home.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.databinding.ViewDataBinding
import cn.wxb.kt.R
import cn.wxb.kt.mvvm.base.BaseActivity
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import com.unity3d.player.UnityPlayer
import kotlinx.android.synthetic.main.activity_unity_3d.flContainer
import kotlinx.android.synthetic.main.activity_unity_3d.tvDoorLeftBack
import kotlinx.android.synthetic.main.activity_unity_3d.tvDoorLeftFront
import kotlinx.android.synthetic.main.activity_unity_3d.tvDoorRightBack
import kotlinx.android.synthetic.main.activity_unity_3d.tvDoorRightFront
import kotlinx.android.synthetic.main.activity_unity_3d.tvDoorTail
import kotlinx.android.synthetic.main.activity_unity_3d.tvFrame
import kotlinx.android.synthetic.main.activity_unity_3d.tvFrameMinus
import kotlinx.android.synthetic.main.activity_unity_3d.tvFramePlus
import kotlinx.android.synthetic.main.activity_unity_3d.tvWindowRoof
import kotlinx.coroutines.launch
import java.lang.RuntimeException

/**
 * 描述: MainActivityV2(viewPage2 + fragment + tabLayout)
 * 创建者: wuxiaobo
 * 邮箱: wuxiaobo@xinzhili.cn
 * 日期: 2020/6/2 11:26
 */
public class MainActivityV2 : BaseActivity<MainViewModel, ViewDataBinding>() , View.OnClickListener{

    companion object {
        fun actionStart(context: Activity) {
            context.startActivityForResult(Intent(context, MainActivityV2::class.java), 1)
        }
    }

    override fun layoutId() = R.layout.activity_unity_3d
    override fun initView(savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            UnityPlayer.UnitySendMessage(
                "MainCamera",
                "OCLoadGlb",
                "/storage/emulated/0/Android/data/cn.wxb.kt/cache/test7.glb"
            )
            Log.e("wxb", ">>>>>>>>>> load glb file <<<<<<<<<")
        }, 3000)

        initView()
        initClickListener()
    }

    override fun initData() {
    }

    private lateinit var mUnityPlayer: UnityPlayer


    private fun initView() {
        mUnityPlayer = UnityPlayer(this)
        flContainer.addView(mUnityPlayer)
        mUnityPlayer.requestFocus()
    }

    private fun initClickListener() {
        mBinding.run {
            tvDoorLeftFront.setOnClickListener(this@MainActivityV2)
            tvDoorLeftBack.setOnClickListener(this@MainActivityV2)
            tvDoorRightFront.setOnClickListener(this@MainActivityV2)
            tvDoorRightBack.setOnClickListener(this@MainActivityV2)

            tvDoorTail.setOnClickListener(this@MainActivityV2)
            tvWindowRoof.setOnClickListener(this@MainActivityV2)

            tvFrameMinus.setOnClickListener(this@MainActivityV2)
            tvFramePlus.setOnClickListener(this@MainActivityV2)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        mUnityPlayer.newIntent(intent)
    }

//    override fun onUnityPlayerUnloaded() {
//        moveTaskToBack(true)
//    }
//
//    override fun onUnityPlayerQuitted() {
//
//    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            mUnityPlayer.lowMemory()
        }
    }

    // Notify Unity of the focus change.
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        mUnityPlayer.windowFocusChanged(hasFocus)
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_MULTIPLE) return mUnityPlayer.injectEvent(event)
        return super.dispatchKeyEvent(event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return mUnityPlayer.onKeyUp(keyCode, event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return mUnityPlayer.onKeyDown(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mUnityPlayer.onTouchEvent(event)
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        return mUnityPlayer.onGenericMotionEvent(event)
    }

    override fun onStart() {
        super.onStart()
        mUnityPlayer.onStart()
    }

    override fun onResume() {
        super.onResume()
        mUnityPlayer.onResume()
    }

    override fun onPause() {
        super.onPause()
        mUnityPlayer.onPause()
    }

    override fun onStop() {
        super.onStop()
        mUnityPlayer.onStop()
    }

    override fun onDestroy() {
        mUnityPlayer.destroy()
        super.onDestroy()
    }

    private var doorLeftFrontState = ActionState.DOOR_CLOSE_LF
    private var doorLeftBackState = ActionState.DOOR_CLOSE_LB

    private var doorRightFrontState = ActionState.DOOR_CLOSE_RF
    private var doorRightBackState = ActionState.DOOR_CLOSE_RB

    private var doorTailState = ActionState.DOOR_CLOSE_TAIL
    private var windowRoofState = ActionState.WINDOW_CLOSE_ROOF

    private val normalColor by lazy {
        resources.getColor(R.color.btn_normal)
    }

    private val selectedColor by lazy {
        resources.getColor(R.color.btn_selected)
    }

    var time = -1L
    override fun onClick(v: View) {
        val curr = System.currentTimeMillis()
        val diff = curr - time
        if(diff < 500){
            return
        }
        time = curr
        when (v.id) {
            R.id.tvDoorLeftFront -> { //左前门
                val txt: String
                val color: Int
                doorLeftFrontState = if (doorLeftFrontState == ActionState.DOOR_CLOSE_LF) {
                    txt = "关闭左前门"
                    color = selectedColor
                    ActionState.DOOR_OPEN_LF
                } else {
                    color = normalColor
                    txt = "打开左前门"
                    ActionState.DOOR_CLOSE_LF
                }
                tvDoorLeftFront.run {
                    text = txt
                    setBackgroundColor(color)
                }
                execAction(doorLeftFrontState)
            }

            R.id.tvDoorLeftBack -> { //左后门
                val txt: String
                val color: Int
                doorLeftBackState = if (doorLeftBackState == ActionState.DOOR_CLOSE_LB) {
                    txt = "关闭左后门"
                    color = selectedColor
                    ActionState.DOOR_OPEN_LB
                } else {
                    color = normalColor
                    txt = "打开左后门"
                    ActionState.DOOR_CLOSE_LB
                }
                tvDoorLeftBack.run {
                    text = txt
                    setBackgroundColor(color)
                }
                execAction(doorLeftBackState)
            }

            R.id.tvDoorRightFront -> { //右前门
                val txt: String
                val color: Int
                doorRightFrontState = if (doorRightFrontState == ActionState.DOOR_CLOSE_RF) {
                    txt = "关闭右前门"
                    color = selectedColor
                    ActionState.DOOR_OPEN_RF
                } else {
                    txt = "打开右前门"
                    color = normalColor
                    ActionState.DOOR_CLOSE_RF
                }
                tvDoorRightFront.run {
                    text = txt
                    setBackgroundColor(color)
                }
                execAction(doorRightFrontState)
            }

            R.id.tvDoorRightBack -> { //右后门
                val txt: String
                val color: Int
                doorRightBackState = if (doorRightBackState == ActionState.DOOR_CLOSE_RB) {
                    txt = "关闭右后门"
                    color = selectedColor
                    ActionState.DOOR_OPEN_RB
                } else {
                    color = normalColor
                    txt = "打开右后门"
                    ActionState.DOOR_CLOSE_RB
                }
                tvDoorRightBack.run {
                    text = txt
                    setBackgroundColor(color)
                }
                execAction(doorRightBackState)
            }

            R.id.tvDoorTail -> { // 后备箱
                val txt: String
                val color: Int
                doorTailState = if (doorTailState == ActionState.DOOR_CLOSE_TAIL) {
                    txt = "关闭后备箱"
                    color = selectedColor
                    ActionState.DOOR_OPEN_TAIL
                } else {
                    txt = "打开后备箱"
                    color = normalColor
                    ActionState.DOOR_CLOSE_TAIL
                }
                tvDoorTail.run {
                    text = txt
                    setBackgroundColor(color)
                }
                execAction(doorTailState)
            }

            R.id.tvWindowRoof -> { // 天窗
                val txt: String
                val color: Int
                windowRoofState = if (windowRoofState == ActionState.WINDOW_CLOSE_ROOF) {
                    txt = "关闭天窗"
                    color = selectedColor
                    ActionState.WINDOW_OPEN_ROOF
                } else {
                    color = normalColor
                    txt = "打开天窗"
                    ActionState.WINDOW_CLOSE_ROOF
                }
                tvWindowRoof.run {
                    text = txt
                    setBackgroundColor(color)
                }
                execAction(windowRoofState)
            }

            R.id.tvFrameMinus -> {
                execFrameAction(false)
            }

            R.id.tvFramePlus -> {
                execFrameAction(isPlus = true)
            }
        }
    }

    private var frameRate = 60
    private fun execFrameAction(isPlus: Boolean) {

        if (isPlus) {
            frameRate += 10
        } else {
            frameRate -= 10
        }

        if (frameRate < 10) {
            frameRate = 10
        } else if (frameRate > 120) {
            frameRate = 120
        }
        val txt = "当前帧率:$frameRate"
        tvFrame.text = txt

        Log.e("unity", ">>>>> modify rate $frameRate")
        UnityPlayer.UnitySendMessage("MainCamera", "setFrameRate", "$frameRate")
    }

    private fun execAction(actionState: ActionState) {
        Log.e("Unity", ">>>>> exec ${actionState.action} <<<<")
        UnityPlayer.UnitySendMessage("MainCamera", "OCSendMessage", actionState.action)
    }
    
    

}
