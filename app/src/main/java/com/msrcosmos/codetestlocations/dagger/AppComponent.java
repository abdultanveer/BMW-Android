package com.msrcosmos.codetestlocations.dagger;

import com.msrcosmos.codetestlocations.locations.presenter.LocationPresenter;
import com.msrcosmos.codetestlocations.locations.view.LocationsActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component (modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(LocationPresenter locationPresenter);

    void inject(LocationsActivity locationsActivity);

}
