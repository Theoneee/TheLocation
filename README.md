### 普通定位(只定位一次，获取到后便会关闭)

## 使用方法

```
implementation 'com.github.Theoneee:TheLocation:1.2'
```

manifests里添加（定位之类的permission不用再自己添加）
```
 <!--这里的value更换成自己的key-->
 <meta-data android:name="com.baidu.lbsapi.API_KEY"
       android:value="bht5LPUxbBAXCi4GHRa8GrMfT8i1vUNR"/>
 <service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote"/>
 ```
 使用前权限要自己动态申请，
```
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
```     



这个只是将百度定位的Demo再进行了下封装，百度定位的Demo SDK写的是26，我在我的项目里按照Demo写，但是SDK写的是27，此时在我8.1的手机上必须要打开GPS才能定位，改成26不打开GPS直接就能定位成功，这个原因还没找到，所以如果要用这个，必须得将SDK改成26...
        





