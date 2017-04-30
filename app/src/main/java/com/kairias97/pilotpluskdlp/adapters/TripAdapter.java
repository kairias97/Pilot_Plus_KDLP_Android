package com.kairias97.pilotpluskdlp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kairias97.pilotpluskdlp.R;
import com.kairias97.pilotpluskdlp.models.Trip;

import java.util.List;

/**
 * Created by kevin on 4/30/2017.
 */

public class TripAdapter extends BaseAdapter {
    private List<Trip> trips;
    private Context context;
    private int layout;

    public TripAdapter(List<Trip> trips, Context context, int layout) {
        this.trips = trips;
        this.context = context;
        this.layout = layout;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return trips.size();
    }

    @Override
    public Object getItem(int position) {
        return trips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(layout ,null);
            vh.name = (TextView) convertView.findViewById(R.id.trip_name);
            vh.origin = (TextView) convertView.findViewById(R.id.origin);
            vh.arrival = (TextView) convertView.findViewById(R.id.arrival);
            vh.create_date = (TextView) convertView.findViewById(R.id.createdAt);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.name.setText(trips.get(position).getTrip_name());
        vh.origin.setText("Departure - " + trips.get(position).getOriginAirport().getName() + "("+trips.get(position).getOriginCountry().getCode() +")");
        vh.arrival.setText("Arrival - " + trips.get(position).getDestinationAirport().getName() + "("+trips.get(position).getDestinationCountry().getCode() +")");
        vh.create_date.setText(trips.get(position).getCreatedAt());
        return convertView;
    }
    public class ViewHolder{
        TextView name;
        TextView origin;
        TextView arrival;
        TextView create_date;
        public ViewHolder(){

        }

    }
}
