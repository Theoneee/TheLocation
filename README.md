# 百度定位(只定位一次，获取到后便会关闭)

## 使用方法
···
implementation 'com.github.Theoneee:TheLocation:1.2'
···

···
 <!--这里的value更换成自己的key-->
 <meta-data android:name="com.baidu.lbsapi.API_KEY"
       android:value="bht5LPUxbBAXCi4GHRa8GrMfT8i1vUNR"/>
 <service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote"/>
 ···
 
 ···
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
  ···      
        





