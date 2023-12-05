package cn.wxb.kt.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wxb.kt.R
import cn.wxb.kt.databinding.FragmentMineBinding
import cn.wxb.kt.service.TestService
import cn.wxb.kt.ui.home.viewmodel.MineViewModel
import cn.wxb.kt.ui.mine.activity.PatientCroListActivity
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mine.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class MineFragment : Fragment() {

    private lateinit var viewModel:MineViewModel
    private lateinit var mBinding:FragmentMineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        // 1。添加此段代码后，主线程异常不会使app崩溃
        Handler(Looper.getMainLooper()).post {
            while (true){
                try {
                    Looper.loop()
                } catch (e:Exception){
                    e.printStackTrace()
                    LogUtils.e("--- loop exception ---")
                }
            }
        }

        // 2。设置此代码，可捕获子线程异常，使app不崩溃
        // 系统设置了KillApplicationHandler
        // 通过setDefaultUncaughtExceptionHandler方法设置了我们自己的崩溃处理器，
        // 就把之前应用设置的这个崩溃处理器给顶掉了，然后我们不做任何处理的话，自然程序就不会崩溃了
        Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler())


        //直接在Activity生命周期内抛出异常，会导致界面绘制无法完成，Activity无法被正确启动，就会白屏或者黑屏
//        throw RuntimeException(">> main thread 异常")
    }

    class AppExceptionHandler : Thread.UncaughtExceptionHandler{
        override fun uncaughtException(t: Thread, e: Throwable) {
            LogUtils.e("catch exception >>>> ${t.name}, ${e.printStackTrace()}")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MineViewModel::class.java]
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)
        mBinding.vm =viewModel
        mBinding.lifecycleOwner = this
//        lifecycle.addObserver(CustomObserver("mine"))
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(view)
        testRvContactAdapter()
        Thread(runnable).start()
    }

    lateinit var concatAdapter:ContentAdapter
    //contactAdapter 测试
    private fun testRvContactAdapter(){
        val titleAdapter = TitleAdapter(mutableListOf("header"))
        val list = mutableListOf<String>()
        for(i in 1..10){
            list.add(">>> content $i <<<")
        }
        val contentAdapter = ContentAdapter(list)
        contentAdapter.setOnItemClickListener { _, view, position -> TODO("Not yet implemented") }


        val footerAdapter = FooterAdapter(mutableListOf("footer"))

        val concatAdapter = ConcatAdapter(titleAdapter, contentAdapter, footerAdapter)
        mBinding.rvList.run {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = concatAdapter
        }


    }




    fun init(view:View){
        tvMineContent.setOnClickListener {
            viewModel.updateTitle("ok")
            PatientCroListActivity.actionStart(requireContext())
        }
        tvTitle.setOnClickListener {
            viewModel.updateTitle("python")
            TestService.enqueueWork(context, Intent())

            //测试主线程异常，使app不崩溃
            throw NullPointerException()
        }
        viewModel.title.observe(viewLifecycleOwner, Observer {
            //ToastUtils.showShort(viewModel.title.value)
        })

        tvVideo.setOnClickListener{
            WebViewActivity.actionStart(requireContext())
//            WebViewActivity.actionStart(requireActivity())

        }

        tvVideoV2.setOnClickListener{
            Thread(runnable2).start()
        }

        tvTouch.setOnClickListener {
            LogUtils.e("wxb_tv", "******* textView onClick ********")
        }
    }

    val runnable2 = Runnable {
        run{
            LogUtils.e(">>>>>> ${Thread.currentThread().name}")
            tvVideo.setText("hello hadfadasfh")
            ivTest.setImageResource(R.drawable.ic_arrow_right_blue)//anr，设置了setDefaultUncaughtExceptionHandler后不再anr
        }
    }

    val runnable = Runnable {
        run{
            Thread.sleep(8000)
            LogUtils.e(">>>>>> ${Thread.currentThread().name}")
            tvVideo.setText("hello hah")
            ivTest.setImageResource(R.drawable.ic_wechat)
        }
    }

    companion object {
        fun newInstance() = MineFragment()
    }
}
