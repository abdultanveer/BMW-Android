package com.msrcosmos.codetestlocations.locations.presenter;

import com.msrcosmos.codetestlocations.locations.view.ILocationsView;



public interface ILocationPresenter {
    void setView(ILocationsView view);
    void loadLocations();
    void sortByName();
    void sortByTime();
}
