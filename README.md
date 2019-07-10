### 普通定位(只定位一次，获取到后便会关闭)

## 使用方法

```
implementation 'com.github.Theoneee:TheLocation:lastVersion'
```

build里添加（定位之类的permission不用再自己添加）

```
 defaultConfig {
        .....
        manifestPlaceholders = [ BAIDU_API_KEY: "你的key"]
    }
```

 使用前权限要自己动态申请，
```
 BdLocationUtil.getInstance().requestLocation(this, new BdLocationUtil.LocationListener() {
            @Override
            public void onReceive(BDLocation location) {
                if (null == location) {
                    tvLocation.setText("定位失败");
                    checkGps();
                    return;
                }
                if (TextUtils.isEmpty(location.getCity())) {
                    checkGps();
                }
                BdLocationUtil.showBd(location);
                tvLocation.setText(location.getAddrStr());
            }
        });
```     
```
   private void checkGps() {
        if (!BdLocationUtil.isOPen(MainActivity.this)) {
            showFailTips("请打开GPS");
        }
    }
```



这个只是将百度定位的Demo再进行了下封装。28的SDK需要判断GPS是否开启。。。
        





