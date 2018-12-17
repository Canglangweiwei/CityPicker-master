package com.rairmmd.citypicker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rairmmd.citypicker.bean.CityInfoBean;
import com.rairmmd.citypicker.utils.RairUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CityPicker {

    private ArrayList<CityInfoBean> mCityListData;
    private Context mContext;

    private static CityPicker instance;

    private CityPicker() {
        mCityListData = new ArrayList<>();
    }

    public static CityPicker getInstance() {
        if (instance == null) {
            synchronized (CityPicker.class) {
                if (instance == null) {
                    instance = new CityPicker();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mContext = context;
        loadCityData();
    }

    /**
     * 解析城市数据
     */
    private void loadCityData() {
        String cityJson = RairUtils.loadJson(mContext, Constants.JSON_DATA);
        Type type = new TypeToken<ArrayList<CityInfoBean>>() {
        }.getType();
        //解析省份
        ArrayList<CityInfoBean> mProvinceBeanArrayList = new Gson().fromJson(cityJson, type);
        if (mProvinceBeanArrayList == null || mProvinceBeanArrayList.isEmpty()) {
            return;
        }
        for (CityInfoBean cityInfoBean : mProvinceBeanArrayList) {
            //每个省份对应下面的市
            ArrayList<CityInfoBean> cityList = cityInfoBean.getCityList();
            //遍历当前省份下面城市的所有数据
            mCityListData.addAll(cityList);
        }
    }

    /**
     * 解析所有的城市数据 357个数据
     */
    public ArrayList<CityInfoBean> getCityListData() {
        return mCityListData;
    }
}
