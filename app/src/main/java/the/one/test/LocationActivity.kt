package the.one.test

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.qmuiteam.qmui.widget.QMUITopBarLayout
import com.theone.mvvm.core.base.activity.BaseCoreActivity
import com.theone.mvvm.core.base.callback.CoreOnPermission
import com.theone.mvvm.ext.qmui.showMsgDialog
import the.one.location.TheLocationManager
import the.one.location.getLog
import the.one.test.databinding.ActivityMainBinding


class LocationActivity : BaseCoreActivity<LocationViewModel, ActivityMainBinding>(),
    AMapLocationListener {

    private var mLocationManager: TheLocationManager? = null

    override fun isExitPage(): Boolean = true

    override fun QMUITopBarLayout.initTopBar() {
        setTitle("定位测试")
    }

    override fun initView(root: View) {
        if(XXPermissions.hasPermission(this,Permission.Group.LOCATION)){
            requestLocation()
        }else{
            privacyCompliance()
        }
    }

    private fun privacyCompliance() {
        AMapLocationClient.updatePrivacyShow(this, true, true)
        val tips = "依据最新的监管要求更新了${getString(R.string.app_name)}《隐私权政策》，特向您说明如下:" +
                "\n1.为向您提供交易相关基本功能，我们会收集、使用必要的信息；" +
                "\n2.基于您的明示授权，我们可能会获取您的位置（为您提供附近的商品、店铺及优惠资讯等）等信息，您有权拒绝或取消授权；" +
                "\n3.我们会采取业界先进的安全措施保护您的信息安全；" +
                "\n4.未经您同意，我们不会从第三方处获取、共享或向提供您的信息；"
        val target = "隐私权政策"
        val start = tips.indexOf(target)
        val spannable =
            SpannableStringBuilder(tips)
        spannable.setSpan(
            ForegroundColorSpan(Color.BLUE),
            start,
            start+target.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        showMsgDialog("温馨提示",spannable, listener = {
            dialog,index->
            dialog.dismiss()
            if(index>0){
                AMapLocationClient.updatePrivacyAgree(this, true)
                requestPermission()
            }else{
                AMapLocationClient.updatePrivacyAgree(this, false)
            }
        })
    }

    private fun requestPermission() {
        XXPermissions.with(this)
            .permission(Permission.Group.LOCATION)
            .request(object : CoreOnPermission(this) {

                override fun hasPermission(granted: MutableList<String>?, all: Boolean) {
                    if (all) {
                        requestLocation()
                    } else {
                        requestPermission()
                    }
                }

            })
    }

    private fun requestLocation() {
        if(null == mLocationManager){
            mLocationManager = TheLocationManager.getInstance().init(this, this)
        }
        showLoading("定位中")
        mLocationManager?.start()
    }

    override fun onLocationChanged(location: AMapLocation?) {
        hideLoading()
        mLocationManager?.stop()
        location?.let {
            getViewModel().address.set(it.getLog())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationManager?.release()
    }

    override fun getBindingClick() = ClickProxy()

    inner class ClickProxy  {

        fun start() {
            requestPermission()
        }

    }

}


