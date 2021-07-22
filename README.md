### 高德地图定位封装(只定位一次，获取到后便会关闭)

## 使用方法

```
implementation 'com.github.Theoneee:TheLocation:lastVersion'
```

build里添加（定位之类的permission不用再自己添加）

```
 defaultConfig {
        .....
        manifestPlaceholders = [ AMAP_KEY: "你申请到的高德地图key"]
    }
```

 使用前权限要自己动态申请，
```
  if(null == locationUtil){
      locationUtil =  TheLocationUtil.getInstance().init(this,this);
   }
  locationUtil.startLocation();
```     
```
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
```



是百度定位有问题还是我太菜了？ 反正SDK到了28就有问题。用百度定位的Demo弄也是有。后台定位如果只有移动网络，几乎只能定位到市了。街道都返回不了。26所有都正常，就是到了28就有问题了。甚至根本无法定位。

那为啥高德就可以？

高德NB！！
        





