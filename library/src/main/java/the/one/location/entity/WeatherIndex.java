package the.one.location.entity;

/**
 * Created by Anastasia on 2018/5/3.
 */

public class WeatherIndex {
    /**
     * des : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
     * tipt : 穿衣指数
     * title : 穿衣
     * zs : 较舒适
     */

    private String des;
    private String tipt;
    private String title;
    private String zs;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    @Override
    public String toString() {
        return "WeatherIndex{" +
                "des='" + des + '\'' +
                ", tipt='" + tipt + '\'' +
                ", title='" + title + '\'' +
                ", zs='" + zs + '\'' +
                '}';
    }
}
