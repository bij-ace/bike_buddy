package com.bike.buddy.bikebuddy;

import com.bike.buddy.bikebuddy.retrofit.model.Location;
import com.bike.buddy.bikebuddy.retrofit.model.Station;
import com.bike.buddy.bikebuddy.retrofit.model.StationResponse;

import java.io.Serializable;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by SFLDPGUSER-27 on 8/24/2018.
 */

public class StationNetwork implements Serializable {

    private List<String> company = null;
    private String href;
    private String id;
    private Location location;
    private String name;
    private String gbfsHref;
    private List<Station> stations;

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGbfsHref() {
        return gbfsHref;
    }

    public void setGbfsHref(String gbfsHref) {
        this.gbfsHref = gbfsHref;
    }

    public String getCompanyName() {
        String address = "";
        for (int i = 0; i < company.size(); i++) {
            address += company.get(i);
        }
        return address;
    }

    public List<Station> getNetwork() {
        return stations;
    }

    public void setNetwork(List<Station> network) {
        this.stations = network;
    }
//    public StationResponse getResponse() {
//        return network;
//    }
//
//    public void setResponse(StationResponse response) {
//        this.network = response;
//    }
}
