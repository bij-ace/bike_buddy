package com.bike.buddy.bikebuddy.retrofit.model;

import com.bike.buddy.bikebuddy.util.ConvertLatLngToAddress;

import java.io.Serializable;
import java.util.List;

public class Station implements Serializable {
    int empty_slots;
    private List<Extra> extra = null;
    int free_bikes;
    String id;
    long latitude;
    long longitude;

    public int getEmpty_slots() {
        return empty_slots;
    }

    public void setEmpty_slots(int empty_slots) {
        this.empty_slots = empty_slots;
    }

    public List<Extra> getExtra() {
        return extra;
    }

    public void setExtra(List<Extra> extra) {
        this.extra = extra;
    }

    public int getFree_bikes() {
        return free_bikes;
    }

    public void setFree_bikes(int free_bikes) {
        this.free_bikes = free_bikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }


    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getAddress(){
        ConvertLatLngToAddress convertLatLngToAddress = new ConvertLatLngToAddress();
        String address = convertLatLngToAddress.getCompleteAddressString(getLatitude(),getLongitude());
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    String name;
    String timestamp;
}
