### 高德地图定位封装

## 使用方法

1.build里添加（manifest里的定位之类的权限声明不用再自己添加，当然添加了也无所谓）

```
 defaultConfig {
        .....
        manifestPlaceholders = [
          AMAP_KEY: "你申请到的高德地图key"
       ]
       ....
    }
    
 dependencies{
   ...
   implementation 'com.github.Theoneee:TheLocation:2.1.0' 
   ...
 }   
   
```

2.初始化
```
   private var mLocationManager: TheLocationManager? = null

   mLocationManager = TheLocationManager.getInstance()?.init(this, this)
   
   #init方法给定了一个默认的Option，可自行传入
   
   fun init(
        context: Context,
        listener: AMapLocationListener,
        options: AMapLocationClientOption? = null
    )

```     

3.开始定位(使用前权限要自己动态申请)

```

   mLocationManager?.start()

```

4.定位回调处理

```
    override fun onLocationChanged(location: AMapLocation?) {
        ....
        mLocationManager?.stop()
        location?.let {
            mViewModel.address.set(it.getLog())
        }
        ...
    }
    
```
5.释放

```
    override fun onDestroy() {
        super.onDestroy()
        mLocationManager?.release()
    }

```

Demo中使用到的框架

[TheCore-MVVM](https://github.com/Theoneee/TheCore-MVVM)
[TheBase-MVVM](https://github.com/Theoneee/TheBase-MVVM)
[TheCommon](https://github.com/Theoneee/TheCommon)
