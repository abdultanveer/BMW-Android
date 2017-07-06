package com.msrcosmos.codetestlocations;

import android.app.Application;

import com.msrcosmos.codetestlocations.dagger.AppComponent;
import com.msrcosmos.codetestlocations.dagger.AppModule;
import com.msrcosmos.codetestlocations.dagger.DaggerAppComponent;


public class LocationsApplication extends Application {

    private AppComponent appComponent;
    public AppComponent getAppComponent() {
        return appComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger(this);
    }
    protected AppComponent initDagger(LocationsApplication application) {

        return  DaggerAppComponent.builder().appModule(new AppModule(application)).build();

    }


}
