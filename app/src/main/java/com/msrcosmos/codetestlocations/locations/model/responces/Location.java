package com.msrcosmos.codetestlocations.locations.model.responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class Location implements Serializable {
    @SerializedName("ID")
    @Expose
    public Integer iD;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Latitude")
    @Expose
    public Float latitude;
    @SerializedName("Longitude")
    @Expose
    public Float longitude;
    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("ArrivalTime")
    @Expose
    public String arrivalTime;
}
