package the.one.location;

import com.baidu.location.BDLocation;

/**
 * @author The one
 * @date 2018/12/3 0003
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public interface LocationListener {

    void onSuccess(BDLocation bdLocation);
    void onFail();

}
