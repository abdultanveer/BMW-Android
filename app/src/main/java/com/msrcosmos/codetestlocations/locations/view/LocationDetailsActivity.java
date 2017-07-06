package com.msrcosmos.codetestlocations.locations.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.msrcosmos.codetestlocations.R;
import com.msrcosmos.codetestlocations.locations.model.responces.Location;
import com.msrcosmos.codetestlocations.utilts.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.locationName)
    TextView locationName;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.time)
    TextView time;
    public Location location;
    Date date = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        ButterKnife.bind(this);


        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        location = (Location) getIntent().getSerializableExtra("location");
        locationName.setText(location.name);
        toolbar.setTitle(location.name);
        address.setText(location.address);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment == null){
            // Create new Map instance if it doesn't exist
            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.map, mapFragment, "map")
                    .commit();
        }
        mapFragment.getMapAsync(this);
        try {
            date = dateFormat.parse(location.arrivalTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if(date.compareTo(new Date())>0){
            time.setText("arrived");

        }else {
            long difference = new Date().getTime() - date.getTime();
            String hours =  DateUtil.hhmm((int)(difference/1000));
            time.setText(hours);
        }



//        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
//        long differenceDates = difference / ( 60 * 60 * 1000);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(location.latitude,location.longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title(location.name));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
//        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
