package com.msrcosmos.codetestlocations.locations.presenter;

import com.msrcosmos.codetestlocations.api.LocationApi;
import com.msrcosmos.codetestlocations.locations.model.responces.Location;
import com.msrcosmos.codetestlocations.locations.view.ILocationsView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LocationPresenter implements ILocationPresenter{
    private ILocationsView view;
    public List<Location> locations = new ArrayList<>();

    @Override
    public void setView(ILocationsView view) {
        this.view = view;

    }

    public void loadLocations() {
        LocationApi api = LocationApi.api();
        Call<List<Location>> call = api.getLocations();
        view.setLoading(true);
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                view.setLoading(false);
                locations.clear();
                locations = response.body();
                if(locations.size()>0){
                    view.setLocations(locations);
                }else {
                    view.setMessage("Something goes wrong ! please try again");
                }

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                view.setLoading(false);
                view.setMessage(t.getMessage());

            }
        });


    }

    @Override
    public void sortByName() {
        Collections.sort(locations, new Comparator<Location>() {
            @Override
            public int compare(Location o1, Location o2) {
                return o1.name.compareToIgnoreCase(o2.name);
            }
        });
        view.setLocations(locations);


    }

    @Override
    public void sortByTime() {
        Collections.sort(locations, new Comparator<Location>() {
            @Override
            public int compare(Location o1, Location o2) {
                int a= 0;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                try {
                    a=  dateFormat.parse(o1.arrivalTime).compareTo(dateFormat.parse(o2.arrivalTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return a;
            }
        });
        view.setLocations(locations);

    }
}
