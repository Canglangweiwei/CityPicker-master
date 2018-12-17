## CityPicker
城市选择器

参考：https://github.com/crazyandcoder/citypicker

## 使用
```
compile 'com.rairmmd:citypicker:1.0.1'
```
![1]

### 1.初始化
建议放到Application中初始化
```
CityPicker.getInstance().init(this);
```
### 2.跳转到选择界面
```
Intent intent = new Intent(this, CitySelectActivity.class);
startActivityForResult(intent, 200);
```

### 3.获取返回的结果
```
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 200 && resultCode == RESULT_OK) {
        CityInfoBean cityinfo = data.getParcelableExtra(Constants.CITY_INFO);
        Log.i("Rair", "(MainActivity.java:31)-onActivityResult:->" + cityinfo.toString());
    }
}
```

## 混淆
```
-keep class com.rairmmd.citypicker.**{
	*;
}
```
[1]:https://github.com/Rairmmd/CityPicker/blob/master/screenshot/screenshot.gif
