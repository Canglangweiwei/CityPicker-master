package com.rairmmd.citypicker.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CityBean implements Parcelable {

    private String id;
    private String name;
    private String pinYin;
    private Double gisGcj02Lat;
    private Double gisGcj02Lng;
    private Double gisBd09Lat;
    private Double gisBd09Lng;
    private String zipcode;

    private ArrayList<DistrictBean> cityList;

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

    public ArrayList<DistrictBean> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<DistrictBean> cityList) {
        this.cityList = cityList;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pinYin);
        dest.writeValue(this.gisGcj02Lat);
        dest.writeValue(this.gisGcj02Lng);
        dest.writeValue(this.gisBd09Lat);
        dest.writeValue(this.gisBd09Lng);
        dest.writeString(this.zipcode);
        dest.writeList(this.cityList);
    }

    public CityBean() {
    }

    protected CityBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.pinYin = in.readString();
        this.gisGcj02Lat = (Double) in.readValue(Double.class.getClassLoader());
        this.gisGcj02Lng = (Double) in.readValue(Double.class.getClassLoader());
        this.gisBd09Lat = (Double) in.readValue(Double.class.getClassLoader());
        this.gisBd09Lng = (Double) in.readValue(Double.class.getClassLoader());
        this.zipcode = in.readString();
        this.cityList = new ArrayList<>();
        in.readList(this.cityList, DistrictBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CityBean> CREATOR = new Parcelable.Creator<CityBean>() {
        @Override
        public CityBean createFromParcel(Parcel source) {
            return new CityBean(source);
        }

        @Override
        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };
}
