package com.bike.buddy.bikebuddy.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class NetworksResponse implements Serializable{

    private List<Network> networks = null;

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

}
