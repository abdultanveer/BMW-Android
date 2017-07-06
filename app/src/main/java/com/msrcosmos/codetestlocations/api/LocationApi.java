package com.msrcosmos.codetestlocations.api;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msrcosmos.codetestlocations.locations.model.responces.Location;


import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LocationApi {
    private static LocationApi api = null;
    private static LocationService service = null;
    private Context context;



    public static LocationApi api(){
        if(api == null){
            api = new LocationApi();
        }
        return api;
    }

    public static LocationService getService(){
        if(service == null){
            OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpclient.addInterceptor(loggingInterceptor);
            OkHttpClient client = httpclient.build();

           Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

            Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URLConstrants.API_BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .client(client)
                                .build();

            service = retrofit.create(LocationService.class);
        }
        return service;
    }

    public Call<List<Location>> getLocations(){
        return getService().getLocations();
    }

}
