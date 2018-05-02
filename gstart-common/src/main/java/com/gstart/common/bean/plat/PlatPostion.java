package com.gstart.common.bean.plat;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-03-24 21:23
 */
public class PlatPostion {
    private String lat;
    private String lng;
    private String province;
    private String district;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "PlatPostion{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
