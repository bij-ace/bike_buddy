package com.bike.buddy.bikebuddy.retrofit.model;

import java.util.List;

public class Network {

    private List<String> company = null;
    private String href;
    private String id;
    private Location location;
    private String name;
    private String gbfsHref;

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

}
