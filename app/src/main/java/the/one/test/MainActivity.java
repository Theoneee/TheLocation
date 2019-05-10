package the.one.test;

import android.Manifest;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import the.one.base.base.activity.BaseActivity;
import the.one.base.base.presenter.BasePresenter;
import the.one.location.BdLocationUtil;

/**
 * @author The one
 * @date 2018/11/30 0030
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tvLocation)
    AppCompatTextView tvLocation;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View mRootView) {
        mTopLayout.setTitle("定位测试");
        requestPermissions();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void requestPermissions() {
        final RxPermissions permissions = new RxPermissions(this);
        permissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE)

                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getLocation();
                    }
                });
    }

    private void getLocation() {
        showLoadingDialog("定位中");
        BdLocationUtil.getInstance().requestLocation(this, new BdLocationUtil.LocationListener() {
            @Override
            public void onReceive(BDLocation location) {
                if (null == location) {
                    tvLocation.setText("定位失败");
                    checkGps();
                    return;
                }
                if (location.getCity().isEmpty()) {
                    checkGps();
                }
                BdLocationUtil.showBd(location);
                tvLocation.setText(location.getAddrStr());
                hideLoadingDialog();
            }
        });
    }

    private void checkGps() {
        if (!BdLocationUtil.isOPen(MainActivity.this)) {
            showFailTips("请打开GPS");
        }
    }


    @OnClick(R.id.btn_re)
    public void onViewClicked() {
        tvLocation.setText("");
        getLocation();
    }
}
