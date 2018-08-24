package com.bike.buddy.bikebuddy.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class StationResponse  implements Serializable {


    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String name;
    public List<Station> stations = null;

    }
