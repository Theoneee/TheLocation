package the.one.location;

import android.annotation.SuppressLint;
import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import the.one.location.service.LocationService;

/**
 * @author The one
 * @date 2018/5/31 0031
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class TheLocation extends BDAbstractLocationListener {

    private static final String TAG = "TheLocation";

    @SuppressLint("StaticFieldLeak")
    private static TheLocation theLocation;

    private Context mContext;
    private LocationListener listener;

    public static TheLocation getInstance() {
        if (theLocation == null)
            theLocation = new TheLocation();
        return theLocation;
    }

    public void init(Context context,LocationListener listener){
        this.mContext = context;
        this.listener = listener;
        initLocation();
    }

    private LocationService locationService;

    /**
     * 初始化定位相关
     */
    private void initLocation() {
        if(null == locationService){
            locationService = new LocationService(mContext);
            locationService.registerListener(this);
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        }
        locationService.start();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if(null !=listener){
            if(null != bdLocation){
                listener.onSuccess(bdLocation);
            }else{
                listener.onFail();
            }
            locationService.unregisterListener(this); //注销掉监听
            locationService.stop();
            mContext = null;
            listener = null;
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
