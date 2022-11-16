package the.one.location

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
/**
 * @author The one
 * @date 2021-07-21 16:47
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
class TheLocationManager private constructor() {

    companion object {

        private var INSTANCE: TheLocationManager? = null

        fun getInstance(): TheLocationManager {
            if (null == INSTANCE) {
                synchronized(TheLocationManager::class) {
                    if (null == INSTANCE) {
                        INSTANCE = TheLocationManager()
                    }
                }
            }
            return INSTANCE!!
        }

    }

    private var mClient: AMapLocationClient? = null

    fun init(
        context: Context,
        listener: AMapLocationListener,
        options: AMapLocationClientOption = defaultOptions()
    ): TheLocationManager {
        if (null == mClient) {
            try {
                mClient = AMapLocationClient(context).apply {
                    setLocationOption(options)
                    setLocationListener(listener)
                }
            }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(context,"请检查用户隐私协议是否显示和同意",Toast.LENGTH_LONG).show()
            }
        }
        return this
    }

    private fun defaultOptions() = AMapLocationClientOption().apply {
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        isGpsFirst = false
        //可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        httpTimeOut = 30000
        //可选，设置定位间隔。默认为2秒
        interval = 2000
        //可选，设置是否返回逆地理地址信息。默认是true
        isNeedAddress = true
        //可选，设置是否单次定位。默认是false
        isOnceLocation = false
        //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        isOnceLocationLatest = false
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP)
        //可选，设置是否使用传感器。默认是false
        isSensorEnable = false
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        isWifiScan = true
        //可选，设置是否使用缓存定位，默认为true
        isLocationCacheEnable = true
    }

    fun start() {
        mClient?.startLocation()
    }

    fun stop() {
        mClient?.stopLocation()
    }

    fun release() {
        mClient = null
    }

}

fun AMapLocation.getLog(): String = StringBuffer().apply {
    // 定位成功
    if (errorCode == 0) {
        append("定位成功\n")
        append("定位类型    : $locationType \n")
        append("经    度    : $longitude \n")
        append("纬    度    : $latitude \n")
        append("精    度    : ${accuracy}米\n")
        append("提供者      : $provider\n")
        append("速    度    : ${speed}米/秒\n")
        append("角    度    : $bearing\n")
        // 获取当前提供定位服务的卫星个数
        append("星    数    : $satellites \n")
        append("国    家    : $country\n")
        append("省          : $province\n")
        append("市          : $city\n")
        append("城市编码    : $cityCode\n")
        append("区          : $district\n")
        append("区域码      : $adCode\n")
        append("地    址    : $address\n")
        append("地    址    : $description\n")
        append("兴趣点      : $poiName\n")
    } else {
        append("定位失败\n")
        append("错误码        : $errorCode\n")
        append("错误信息      : $errorInfo\n")
        append("错误描述      : $locationDetail\n")
    }
    append("***定位质量报告***\n")
    append("* WIFI开关：${if (locationQualityReport.isWifiAble) "开启" else "关闭"}\n")
    append("* GPS星数：${locationQualityReport.gpsSatellites}\n")
    append("****************\n")
}.toString()
