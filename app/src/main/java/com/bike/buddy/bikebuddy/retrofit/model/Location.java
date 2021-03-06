package com.bike.buddy.bikebuddy.retrofit.model;

import com.bike.buddy.bikebuddy.util.ConvertLatLngToAddress;

import java.io.Serializable;

public class Location implements Serializable{

    private String city;
    private String country;
    private Double latitude;
    private Double longitude;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress(){
        ConvertLatLngToAddress convertLatLngToAddress = new ConvertLatLngToAddress();
        String address = convertLatLngToAddress.getCompleteAddressString(getLatitude(),getLongitude());
        return address;
    }
}
