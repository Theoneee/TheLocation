package the.one.test;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import the.one.base.base.activity.BaseActivity;
import the.one.base.base.presenter.BasePresenter;
import the.one.location.TheLocationUtil;

/**
 * @author The one
 * @date 2018/11/30 0030
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class MainActivity extends BaseActivity implements AMapLocationListener {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
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

    private TheLocationUtil locationUtil;

    private void getLocation() {
        showLoadingDialog("定位中");
        if(null == locationUtil){
            locationUtil =  TheLocationUtil.getInstance().init(this,this);
        }
        locationUtil.startLocation();
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        hideLoadingDialog();
        locationUtil.stopLocation();
        TheLocationUtil.showLog(aMapLocation);
        if (null == aMapLocation || TextUtils.isEmpty(aMapLocation.getCity())) {
            tvLocation.setText("定位失败");
            showFailDialog();
            return;
        }
        tvLocation.setText(aMapLocation.getAddress());
    }


    private void showFailDialog(){
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("无法定位到您的位置，请打开GPS定位服务。")
                .addAction("退出", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                })
                .addAction(0, "重新定位", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        getLocation();
                    }
                })
                .setCanceledOnTouchOutside(false)
                .show().setOnKeyListener(keyListener);
    }

    private DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
            return keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0;
        }
    };

    @OnClick(R.id.btn_re)
    public void onViewClicked() {
        tvLocation.setText("");
        getLocation();
    }

}
