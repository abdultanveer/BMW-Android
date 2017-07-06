package com.msrcosmos.codetestlocations.dagger;

import com.msrcosmos.codetestlocations.locations.presenter.LocationPresenter;

import dagger.Module;
import dagger.Provides;



@Module
public class PresenterModule {

    @Provides
    LocationPresenter provideLocationProvider() {
        return new LocationPresenter();

    }
}
