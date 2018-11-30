package the.one.location.entity;

/**
 * Created by Anastasia on 2018/5/3.
 */

public class WeatherData {
    /**
     * date : 周四 05月03日 (实时：19℃)
     * dayPictureUrl : http://api.map.baidu.com/images/weather/day/yin.png
     * nightPictureUrl : http://api.map.baidu.com/images/weather/night/yin.png
     * weather : 阴
     * wind : 东北风3-4级
     * temperature : 23 ~ 18℃
     */

    private String date;
    private String dayPictureUrl;
    private String nightPictureUrl;
    private String weather;
    private String wind;
    private String temperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return  date + '\n' +
                weather + '\n' +
                wind + '\n' +
                temperature ;
    }
}
