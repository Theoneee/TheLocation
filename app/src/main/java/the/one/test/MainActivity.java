package the.one.test;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import the.one.location.LocationListener;
import the.one.location.TheLocation;

/**
 * @author The one
 * @date 2018/11/30 0030
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvLocation);
        requestPermissions();
    }

    private void requestPermissions() {
        final RxPermissions permissions = new RxPermissions(this);
        permissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        // 删除更新包
                        getLocation();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getLocation(){
        Log.e(TAG, "getLocation: " );
        TheLocation.getInstance().init(this, new LocationListener() {
            @Override
            public void onSuccess(BDLocation bdLocation) {
                textView.setText(bdLocation.getAddrStr());
            }

            @Override
            public void onFail() {
                textView.setText("定位失败");
            }
        });
    }
}
