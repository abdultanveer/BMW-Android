package com.msrcosmos.codetestlocations.api;

import com.msrcosmos.codetestlocations.locations.model.responces.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface LocationService {
    @GET("/api/Locations")
    public Call<List<Location>> getLocations();

}
