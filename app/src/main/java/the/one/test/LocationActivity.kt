package the.one.test

import android.Manifest
import android.view.View
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationListener
import com.hjq.permissions.OnPermission
import com.hjq.permissions.XXPermissions
import com.theone.mvvm.base.IClick
import com.theone.mvvm.core.base.activity.BaseCoreActivity
import the.one.location.TheLocationManager
import the.one.location.getLog
import the.one.test.databinding.ActivityMainBinding

class LocationActivity : BaseCoreActivity<LocationViewModel, ActivityMainBinding>(),
    AMapLocationListener {

    private var mLocationManager: TheLocationManager? = null

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun isExitPage(): Boolean = true

    override fun initView(root: View) {
        getTopBar()?.setTitle("定位测试")
        mLocationManager = TheLocationManager.getInstance()?.init(this, this)
        requestPermission()
    }

    private fun requestPermission() {
        XXPermissions.with(this).permission(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
        ).request(object : OnPermission {

            override fun hasPermission(granted: MutableList<String>?, all: Boolean) {
                if (all) {
                    showLoading("定位中")
                    mLocationManager?.start()
                }
            }

            override fun noPermission(denied: MutableList<String>?, quick: Boolean) {

            }

        })
    }

    override fun createObserver() {

    }

    override fun onLocationChanged(location: AMapLocation?) {
        hideLoading()
        mLocationManager?.stop()
        location?.let {
            mViewModel.address.set(it.getLog())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationManager?.release()
    }

    override fun getBindingClick(): IClick = ClickProxy()

    inner class ClickProxy : IClick {

        fun start() {
            requestPermission()
        }

    }

}


