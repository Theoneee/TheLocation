package the.one.test;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import the.one.base.Interface.IOnKeyBackClickListener;
import the.one.base.base.activity.BaseActivity;
import the.one.base.base.activity.BaseFragmentActivity;
import the.one.base.base.fragment.BaseFragment;
import the.one.base.base.presenter.BasePresenter;
import the.one.base.util.StatusBarUtil;
import the.one.base.widge.MyTopBarLayout;
import the.one.location.TheLocationUtil;

/**
 * @author The one
 * @date 2018/11/30 0030
 * @describe TODO
 * @email 625805189@qq.com
 * @remark
 */
public class LocationActivity extends BaseFragmentActivity {


    @Override
    protected BaseFragment getBaseFragment() {
        return new LocationFragment();
    }



}
