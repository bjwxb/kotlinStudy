package cn.wxb.kt.ui.home.fragment

import android.webkit.JavascriptInterface
import cn.wxb.kt.ui.home.fragment.webview.CompletionHandler
import com.blankj.utilcode.util.*
import com.google.gson.JsonObject
import org.json.JSONObject

/**
 * @desc:
 * @author: wuxiaobo
 * @date: 2022/7/11 15:00
 */
class JsApi {

    @JavascriptInterface
    fun close(data:Any?){
        ActivityUtils.getTopActivity().finish()
    }

    //2. 需要获取用户信息方法名Storage.userInfo
    //- 返回内容应为JSON字符串；例：{accessToken:"abcd",beanId:"112"}
    //- 需要返回用户的token，参数名为accessToken
    //- 需要返回用户的beanId，参数名为beanId
    @JavascriptInterface
    fun userInfo(data:Any?):String{
        val map = HashMap<String, String>()
        map.put("accessToken", "token")
        map.put("beanId", "idxxxxid")
        return GsonUtils.toJson(map)
    }

    //待定
    // 3. 需要获取车辆的vin方法名为：Storage.userCarInfo
    //- 返回内容应为JSON字符串；例：{vin:"1234abc"}
    //- 需要返回车辆的vin，参数名为vin
    @JavascriptInterface
    fun userCarInfo(data:Any?):JSONObject{
        val map = HashMap<String, String>()
        map["vin"] = "BBCDABCDA161"
        LogUtils.e(">>>>> $map")
        val jsonObject = JSONObject()
        jsonObject.put("vin", "12")
        return jsonObject
    }


    //7、功能：获取 应用与设备信息 网络GPS
    //- 方法名Storage.getAPPInfo
    //js使用例子：
    //dsBridge.call("Storage.getAPPInfo", function(data) {
    //const deviceInformation = data;
    //});
    //
    //{"app_id":"4a9cb01712ed0c31fcbfb631b4dc6a65",
    // "app_name":"wey_android",
    // "app_package":"com.navinfo.gwead",
    // "brand":"Xiaomi",
    // "device_id":"88bf630258cc0093",
    // "device_resolution":"1080*2206",
    // "device_type":"mb","end_time":"
    // ","idc_model":"","imei":"","imsi":"",
    // "ip":"172.18.73.203","is_gps":"true",
    // "latitude":"","longitude":"",
    // "mac":"02:00:00:00:00:00",
    // "model":"M2007J1SC","net_work":"",
    // "os":"Android",
    // "os_vers":"31","pushcontent":"",
    // "pushstart":"","signal_intensity":"",
    // "source":"wey",
    // "start_time":"","versionName":"3.3.730"}
    @JavascriptInterface
    fun getAPPInfo(data:Any?, handler:CompletionHandler<Any>){
        val map = HashMap<String, String>()
        map["app_name"] = AppUtils.getAppName()
        map["brand"] = DeviceUtils.getManufacturer()
        map["device_id"] = DeviceUtils.getUniqueDeviceId()
        map["device_resolution"] = ScreenUtils.getScreenWidth().toString() + "*" + ScreenUtils.getScreenHeight()
        map["versionName"] = AppUtils.getAppVersionName()
        map["os"] = "Android"
        map["os_vers"] = DeviceUtils.getSDKVersionCode().toString()

        handler.complete(GsonUtils.toJson(map))
    }

    //8、功能：获取原生定位地址
    //- 方法名Storage.mallLocationInfo
    //js使用例子：
    //dsBridge.call("Storage.mallLocationInfo", function(data) {
    //console.log(data, "Storage.mallLocationInfo");
    //});
    //- 返回一个对象{longitude:"",latitude:"",province:"",city:"",district:"",street:"",number:""}
    // // city: '上海市',
    //  // district: '长宁区',
    //  // latitude: 31.20215521918403,
    //  // longitude: 121.40282606336805,
    //  // number: '35号',
    //  // province: '上海市',
    //  // street: '娄山关路'
    @JavascriptInterface
    fun mallLocationInfo(data:Any?, handler:CompletionHandler<Any>){

        val map = HashMap<String, Any>()
        map["city"] = AppUtils.getAppName()
        map["district"] = AppUtils.getAppName()
        map["latitude"] = AppUtils.getAppName()
        map["longitude"] = AppUtils.getAppName()
        map["number"] = AppUtils.getAppName()
        map["province"] = AppUtils.getAppName()
        map["street"] = AppUtils.getAppName()
        handler.complete(GsonUtils.toJson(map))
    }

}