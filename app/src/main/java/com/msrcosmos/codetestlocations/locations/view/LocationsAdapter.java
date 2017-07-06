package com.msrcosmos.codetestlocations.locations.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msrcosmos.codetestlocations.R;
import com.msrcosmos.codetestlocations.locations.model.responces.Location;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {
    public List<Location> locations = new ArrayList<>();
    public Context context;
    public ISelectedLocation aInterface;

    public LocationsAdapter(List<Location> locations, Context context) {
        this.locations.clear();
        this.locations.addAll(locations);
        this.context = context;
    }

    public void setLocations(List<Location> locations){
        this.locations.clear();
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.location_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.locationName.setText(location.name);
        holder.locationAddress.setText(location.address);

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.locationName)
        TextView locationName;
        @BindView(R.id.locationAddress)
        TextView locationAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aInterface.selectedLocation(locations.get(getAdapterPosition()),getAdapterPosition());
                }
            });


        }
    }
}
