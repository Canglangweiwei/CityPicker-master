package com.rairmmd.citypick.app;

import android.app.Application;

import com.rairmmd.citypicker.CityPicker;

public class MyGlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化城市列表选择器
        CityPicker.getInstance().init(this);
    }
}
