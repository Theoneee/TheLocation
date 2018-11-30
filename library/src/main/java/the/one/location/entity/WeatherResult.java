package the.one.location.entity;

import java.util.List;

/**
 * Created by Anastasia on 2018/5/3.
 */

public class WeatherResult {
    /**
     * currentCity : 铜仁市
     * pm25 : 79
     * index : [{"des":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。","tipt":"穿衣指数","title":"穿衣","zs":"较舒适"},{"des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","tipt":"洗车指数","title":"洗车","zs":"较适宜"},{"des":"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。","tipt":"感冒指数","title":"感冒","zs":"较易发"},{"des":"阴天，较适宜进行各种户内外运动。","tipt":"运动指数","title":"运动","zs":"较适宜"},{"des":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。","tipt":"紫外线强度指数","title":"紫外线强度","zs":"最弱"}]
     * weather_data : [{"date":"周四 05月03日 (实时：19℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"阴","wind":"东北风3-4级","temperature":"23 ~ 18℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"阴","wind":"无持续风向微风","temperature":"23 ~ 18℃"},{"date":"周六","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/leizhenyu.png","weather":"多云转雷阵雨","wind":"无持续风向微风","temperature":"30 ~ 21℃"},{"date":"周日","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/leizhenyu.png","weather":"多云转雷阵雨","wind":"无持续风向微风","temperature":"29 ~ 18℃"}]
     */

    private String currentCity;
    private String pm25;
    private List<WeatherIndex> index;
    private List<WeatherData> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<WeatherIndex> getIndex() {
        return index;
    }

    public void setIndex(List<WeatherIndex> index) {
        this.index = index;
    }

    public List<WeatherData> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<WeatherData> weather_data) {
        this.weather_data = weather_data;
    }

    @Override
    public String toString() {
        return "WeatherResult{" +
                "currentCity='" + currentCity + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", index=" + index +
                ", weather_data=" + weather_data +
                '}';
    }
}
