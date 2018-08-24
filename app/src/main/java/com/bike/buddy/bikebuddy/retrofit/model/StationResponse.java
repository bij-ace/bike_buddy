package com.bike.buddy.bikebuddy.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class StationResponse  implements Serializable {

    public List<Network> networks = null;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String name;
    public List<Station> stations = null;


    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    }
