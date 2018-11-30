package the.one.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * @author The one
 * @date 2018/5/31 0031
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class TheLocation implements BDLocationListener {

    private static final String TAG = "TheLocation";

    @SuppressLint("StaticFieldLeak")
    public static TheLocation theLocation;

    private LocationClient mLocClient;

    private Context mContext;
    private CityBackListener cityBackListener;


    public TheLocation(Activity context) {
        mContext = context;
    }

    public static TheLocation getInstance(Activity activity) {
        if (theLocation == null)
            theLocation = new TheLocation(activity);
        return theLocation;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Log.e(TAG, "onReceiveLocation: " );
        String city = null;
        if (bdLocation != null) {
            // getCity  市   getDistrict  可以获取到区、县，这样获取的天气更准确
            city = bdLocation.getDistrict();
        }
        Log.e(TAG, "onReceiveLocation:  city = "+city );
        if (null != cityBackListener)
            cityBackListener.onCityBack(city, bdLocation);
    }

    public interface CityBackListener {
        void onCityBack(String city, BDLocation bdLocation);
    }

    public void init(CityBackListener cityBackListener) {
        this.cityBackListener = cityBackListener;
        initLocation();
    }

    /**
     * 初始化定位相关
     */
    private void initLocation() {
        // 定位初始化
        if (mLocClient == null) {
            mLocClient = new LocationClient(mContext);
            mLocClient.registerLocationListener(this);
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            // *** 可选，设置是否需要地址信息，默认不需要  这里是获取地址信息 所以必须加上 ***//
            option.setIsNeedAddress(true);
            mLocClient.setLocOption(option);
        }
        mLocClient.start();
    }

    /**
     * 获取天气
     */
//    private void getWeather(String city ){
//        showLog(city);
//        String url = "http://api.map.baidu.com/telematics/v3/weather";
//        OkHttpUtils
//                .get()
//                .url(url)
//                .addParams("location", city)
//                .addParams("output", "json")
//                .addParams("ak", "T2AHbrsEn5FaSg4Iir8YfP1U")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        showLog("onError");
//                        onWeatherListner.onWeahter(e.toString());
//                    }
//                    @Override
//                    public void onResponse(String response, int id) {
//                        showLog("onResponse = "+response);
//                        Gson gson = new Gson();
//                        Weather weather = gson.fromJson(response, Weather.class);
//                        if (weather.getError() == 0) {
//                            // 获取天气数据成功
//                           WeatherResult weatherResult = weather.getResults().get(0);
//                            onWeatherListner.onWeahter(weatherResult.getWeather_data().get(0).getDate());
//                            showLog("result");
//                        }else{
//                            onWeatherListner.onWeahter("");
//                        }
//                    }
//                });
//    }
}
