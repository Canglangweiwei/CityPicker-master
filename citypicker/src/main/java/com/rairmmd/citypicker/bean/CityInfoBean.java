package com.rairmmd.citypicker.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CityInfoBean implements Parcelable {

    private String fistLetter;
    private int sort;
    private String id;
    private String name;
    private String pinYin;
    private Double gisGcj02Lat;
    private Double gisGcj02Lng;
    private Double gisBd09Lat;
    private Double gisBd09Lng;
    private String zipcode;

    private ArrayList<CityInfoBean> cityList;

    public ArrayList<CityInfoBean> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<CityInfoBean> cityList) {
        this.cityList = cityList;
    }

    public CityInfoBean() {
        super();
    }

    public static CityInfoBean findCity(List<CityInfoBean> list, String cityName) {
        try {
            for (int i = 0; i < list.size(); i++) {
                CityInfoBean city = list.get(i);
                if (cityName.equals(city.getName())
                        || cityName.contains(city.getName())
                        || city.getName().contains(cityName)) {
                    return city;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public String getFistLetter() {
        return fistLetter == null ? "" : fistLetter;
    }

    public void setFistLetter(String fistLetter) {
        this.fistLetter = fistLetter;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin == null ? "" : pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public Double getGisGcj02Lat() {
        return gisGcj02Lat == null ? Double.valueOf(0.0d) : gisGcj02Lat;
    }

    public void setGisGcj02Lat(Double gisGcj02Lat) {
        this.gisGcj02Lat = gisGcj02Lat;
    }

    public Double getGisGcj02Lng() {
        return gisGcj02Lng == null ? Double.valueOf(0.0d) : gisGcj02Lng;
    }

    public void setGisGcj02Lng(Double gisGcj02Lng) {
        this.gisGcj02Lng = gisGcj02Lng;
    }

    public Double getGisBd09Lat() {
        return gisBd09Lat == null ? Double.valueOf(0.0d) : gisBd09Lat;
    }

    public void setGisBd09Lat(Double gisBd09Lat) {
        this.gisBd09Lat = gisBd09Lat;
    }

    public Double getGisBd09Lng() {
        return gisBd09Lng == null ? Double.valueOf(0.0d) : gisBd09Lng;
    }

    public void setGisBd09Lng(Double gisBd09Lng) {
        this.gisBd09Lng = gisBd09Lng;
    }

    public String getZipcode() {
        return zipcode == null ? "" : zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fistLetter);
        dest.writeInt(this.sort);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pinYin);
        dest.writeValue(this.gisGcj02Lat);
        dest.writeValue(this.gisGcj02Lng);
        dest.writeValue(this.gisBd09Lat);
        dest.writeValue(this.gisBd09Lng);
        dest.writeString(this.zipcode);
        dest.writeTypedList(this.cityList);
    }

    private CityInfoBean(Parcel in) {
        this.fistLetter = in.readString();
        this.sort = in.readInt();
        this.id = in.readString();
        this.name = in.readString();
        this.pinYin = in.readString();
        this.gisGcj02Lat = (Double) in.readValue(Double.class.getClassLoader());
        this.gisGcj02Lng = (Double) in.readValue(Double.class.getClassLoader());
        this.gisBd09Lat = (Double) in.readValue(Double.class.getClassLoader());
        this.gisBd09Lng = (Double) in.readValue(Double.class.getClassLoader());
        this.zipcode = in.readString();
        this.cityList = in.createTypedArrayList(CityInfoBean.CREATOR);
    }

    public static final Creator<CityInfoBean> CREATOR = new Creator<CityInfoBean>() {
        @Override
        public CityInfoBean createFromParcel(Parcel source) {
            return new CityInfoBean(source);
        }

        @Override
        public CityInfoBean[] newArray(int size) {
            return new CityInfoBean[size];
        }
    };
}
