package cn.wxb.kt.ui.home.fragment

//import io.flutter.embedding.android.FlutterActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wxb.kt.R
import cn.wxb.kt.databinding.FragmentHomeBinding
import cn.wxb.kt.mvvm.base.BaseFragment
import cn.wxb.kt.ui.home.viewmodel.MainViewModel
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import cn.wxb.kt.ui.home.activity.MainActivityV2


class HomeFragment : BaseFragment<MainViewModel, FragmentHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    //与Activity共享viewModel
    override fun isShareVM() = true

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.vm = viewModel
        viewModel.name.observe(this, Observer {
            LogUtils.e("============= ${it}")
        })
        testRvContactAdapter()


        LogUtils.getConfig().isLog2FileSwitch  = true
        LogUtils.file("haha")

    }

    private fun renderIv(){
        mBinding?.run {
            val param = ivTestWH.layoutParams as LinearLayout.LayoutParams
            param.width = ScreenUtils.getScreenWidth()/2
            param.height = ScreenUtils.getScreenWidth()/2
            ivTestWH.layoutParams = param
        }
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
        mBinding?.rvHome?.run {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = concatAdapter
        }


    }
    override fun lazyLoadData() {
        LogUtils.e(">>>>>>>>>>>>>> lazyLoadData")
        LogUtils.e("${requireActivity().taskId}>>>>>> ${requireActivity().callingActivity}>>>> ${requireActivity().parent}")

        init()
    }

    override fun show() {
        LogUtils.e("------------ fragment show ---------------")
    }

    private fun init(){
        viewModel.mPatientInfo.observe(this, Observer {
            LogUtils.e(Gson().toJson(it))
        })
        btnModifyVmValue.setOnClickListener {
            viewModel.name.value = "java"
//            renderIv()
//            testGps()
//            requireActivity().finishAffinity()
        }

        tvContent.setOnClickListener {
            LogUtils.e(">>>>>> ${requireActivity().callingActivity}>>>> ${requireActivity().parent}")

            MainActivityV2.actionStart(requireActivity())
//            PatientListActivity.actionStart(activity!!)
//            MineOrderActivity.actionStart(activity!!)
//            startActivity(
//                FlutterActivity.withNewEngine()
//                    .initialRoute("route2")
//                    .build(mContext)
//            )
        }
    }

    fun testGps(){
        val gps = gps84_To_Gcj02(38.827953, 115.454864)
        LogUtils.e(GsonUtils.toJson(gps))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    var pi = 3.1415926535897932384626
    var a = 6378245.0
    var ee = 0.00669342162296594323

    fun gps84_To_Gcj02(lat: Double, lon: Double): GPS? {
        if (outOfChina(lat, lon)) {
            return null
        }
        var dLat: Double = transformLat(lon - 105.0, lat - 35.0)
        var dLon: Double = transformLon(lon - 105.0, lat - 35.0)
        val radLat: Double = lat / 180.0 * pi
        var magic = Math.sin(radLat)
        magic = 1 - ee * magic * magic
        val sqrtMagic = Math.sqrt(magic)
        dLat = dLat * 180.0 / (a * (1 - ee) / (magic * sqrtMagic) * pi)
        dLon = dLon * 180.0 / (a / sqrtMagic * Math.cos(radLat) * pi)
        val mgLat = lat + dLat
        val mgLon = lon + dLon
        return GPS(mgLat, mgLon)
    }

    fun outOfChina(lat: Double, lon: Double): Boolean {
        if (lon < 72.004 || lon > 137.8347) return true
        return if (lat < 0.8293 || lat > 55.8271) true else false
    }

    fun transform(lat: Double, lon: Double): GPS? {
        if (outOfChina(lat, lon)) {
            return GPS(lat, lon)
        }
        var dLat = transformLat(lon - 105.0, lat - 35.0)
        var dLon = transformLon(lon - 105.0, lat - 35.0)
        val radLat = lat / 180.0 * pi
        var magic = Math.sin(radLat)
        magic = 1 - ee * magic * magic
        val sqrtMagic = Math.sqrt(magic)
        dLat = dLat * 180.0 / (a * (1 - ee) / (magic * sqrtMagic) * pi)
        dLon = dLon * 180.0 / (a / sqrtMagic * Math.cos(radLat) * pi)
        val mgLat = lat + dLat
        val mgLon = lon + dLon
        return GPS(mgLat, mgLon)
    }

    fun transformLat(x: Double, y: Double): Double {
        var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x))
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0
        return ret
    }


    fun transformLon(x: Double, y: Double): Double {
        var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + (0.1
                * Math.sqrt(Math.abs(x)))
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(
            x / 30.0
                    * pi
        )) * 2.0 / 3.0
        return ret
    }
}

/**
 * 坐标对象，由经纬度构成
 * Created by xfkang on 2018/3/28.
 */
class GPS(var lat: Double, var lon: Double) {

    override fun toString(): String {
        return "lat:$lat,lon:$lon"
    }
}



