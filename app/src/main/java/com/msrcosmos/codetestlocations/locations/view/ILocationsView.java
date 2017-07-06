package com.msrcosmos.codetestlocations.locations.view;

import com.msrcosmos.codetestlocations.locations.model.responces.Location;

import java.util.List;



public interface ILocationsView {
    void setLocations(List<Location> locations);
    void setMessage(String message);
    void setLoading(boolean visible);
}
