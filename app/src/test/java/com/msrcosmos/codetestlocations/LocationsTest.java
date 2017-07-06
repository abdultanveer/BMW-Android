package com.msrcosmos.codetestlocations;

import com.msrcosmos.codetestlocations.locations.presenter.LocationPresenter;
import com.msrcosmos.codetestlocations.locations.view.ILocationsView;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;



public class LocationsTest {

    @Test
    public void getLocations() throws Exception {
        CountDownLatch signal = new CountDownLatch(1);
        ILocationsView locationsView = mock(ILocationsView.class);
        LocationPresenter locationPresenter = new LocationPresenter();
        locationPresenter.setView(locationsView);
        locationPresenter.loadLocations();
        signal.await(8, TimeUnit.SECONDS);
        verify(locationsView).setLoading(false);
        verify(locationsView).setLocations(locationPresenter.locations);
//        signal.await();

    }
}
