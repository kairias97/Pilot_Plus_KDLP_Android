package com.kairias97.pilotpluskdlp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kairias97.pilotpluskdlp.R;
import com.kairias97.pilotpluskdlp.models.Airport;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by kevin on 4/29/2017.
 */

public class AirportAdapter extends BaseAdapter {
    private List<Airport> airports;
    private Context context;
    private int layout;

    public AirportAdapter(List<Airport> airports, Context context, int layout) {
        this.airports = airports;
        this.context = context;
        this.layout = layout;
    }
    public void setAirports(List<Airport> airports){
        this.airports = airports;
    }
    public int getCountryPos(Airport a){
        return this.airports.indexOf(a);
    }
    @Override
    public int getCount() {
        return airports.size();
    }

    @Override
    public Object getItem(int position) {
        return airports.get(position);
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
            vh.itemName = (TextView) convertView.findViewById(R.id.item_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.itemName.setText(airports.get(position).getName());
        return convertView;
    }

    public class ViewHolder{
        TextView itemName;
        public ViewHolder(){
        }
    }
}
