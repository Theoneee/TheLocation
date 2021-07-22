package the.one.test;

import android.Manifest;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import the.one.base.base.fragment.BaseFragment;
import the.one.base.base.presenter.BasePresenter;
import the.one.location.TheLocationUtil;

public class LocationFragment extends BaseFragment implements AMapLocationListener, View.OnClickListener{

    @BindView(R.id.tvLocation)
    AppCompatTextView tvLocation;
    private QMUIAlphaImageButton mRefresh;

    private TheLocationUtil locationUtil;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_location;
    }

    @Override
    protected void initView(View rootView) {
        mTopLayout.setTitle("定位测试");
        mRefresh = mTopLayout.addRightImageButton(R.drawable.icon_refresh_dark,R.id.topbar_right_button1);
        mRefresh.setOnClickListener(this);
        locationUtil =  TheLocationUtil.getInstance().init(_mActivity,this);
        goneView(mRefresh);
        requestPermissions();
    }

    private void requestLocation() {
        showContentPage();
        showLoadingDialog("定位中");
        goneView(mRefresh,tvLocation);
        locationUtil.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        hideLoadingDialog();
        locationUtil.stopLocation();
        if (null == aMapLocation ) {
            showEmptyPage("定位失败", "重新定位", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestLocation();
                }
            });
            return;
        }
        showContentPage();
        tvLocation.setText(TheLocationUtil.getResult(aMapLocation));
        showView(mRefresh,tvLocation);
    }

    @Override
    public void onClick(View v) {
        tvLocation.setText("");
        requestLocation();
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
                        if(!aBoolean){
                            showEmptyPage("需要定位权限", "申请权限", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestPermissions();
                                }
                            });
                        }else{
                            requestLocation();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected boolean isExitFragment() {
        return true;
    }

}
