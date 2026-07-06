package com.home.subms.model;

import com.mongodb.lang.NonNull;

public class LookUpCityCountry{

    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
    private String dialCode;
    private String isoCode;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    @NonNull
    public String toString() {
        return "LookUpCityCountry{" +
                "cityId='" + cityId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", dialCode='" + dialCode + '\'' +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }
}

