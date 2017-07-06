package com.msrcosmos.codetestlocations.locations.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.msrcosmos.codetestlocations.LocationsApplication;
import com.msrcosmos.codetestlocations.R;
import com.msrcosmos.codetestlocations.locations.model.responces.Location;
import com.msrcosmos.codetestlocations.locations.presenter.LocationPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LocationsActivity extends AppCompatActivity implements ILocationsView, ISelectedLocation {

    @Inject
    LocationPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private List<Location> locations = new ArrayList<>();
    private LocationsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locations_view);
        ((LocationsApplication) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);
        toolbar.setTitle("Locations");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        presenter.setView(this);
        if (savedInstanceState != null) {
            locations = (List<Location>) savedInstanceState.getSerializable("locations");
            presenter.locations.clear();
            presenter.locations.addAll(locations);


        }
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new LocationsAdapter(locations, getApplicationContext());
        recyclerview.setAdapter(adapter);
        adapter.aInterface = this;
        if (locations.size() == 0) {
            presenter.loadLocations();
        }


    }


    @Override
    public void setLocations(List<Location> locations) {
        progressBar.setVisibility(View.GONE);
        this.locations.clear();
        this.locations.addAll(locations);
        adapter.setLocations(locations);

    }

    @Override
    public void setMessage(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sortby_time) {
            if (presenter != null) {
                presenter.sortByTime();
                //progressBar.setVisibility(View.VISIBLE);

            }

            return true;
        } else if (id == R.id.action_sortby_name) {
            if (presenter != null) {
                presenter.sortByName();
                //progressBar.setVisibility(View.VISIBLE);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedLocation(Location location, int position) {
        Intent intent = new Intent(this, LocationDetailsActivity.class);
        intent.putExtra("location", (Serializable) location);
        startActivity(intent);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("locations", (Serializable) locations);

    }

    @Override
    public void setLoading(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
