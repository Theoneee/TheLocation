package the.one.location;

import android.annotation.SuppressLint;
import android.content.Context;

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

    public static TheLocation getInstance() {
        if (theLocation == null)
            theLocation = new TheLocation();
        return theLocation;
    }

    public void init(Context context,CityBackListener cityBackListener){
        this.mContext = context;
        this.cityBackListener = cityBackListener;
        initLocation();
    }

    public void start(){
        mLocClient.start();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String city = null;
        if (bdLocation != null) {
            // getCity  市   getDistrict  可以获取到区、县，这样获取的天气更准确
            city = bdLocation.getDistrict();
        }
        if (null != cityBackListener){
            if(null == city)
                cityBackListener.onError("获取失败");
            else
                cityBackListener.onCityBack(city, bdLocation);
        }
        if(null != mLocClient){
            mLocClient.unRegisterLocationListener(this);
            mLocClient.stop();
        }
    }

    public interface CityBackListener {
        void onCityBack(String city, BDLocation bdLocation);
        void onError(String msg);
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
