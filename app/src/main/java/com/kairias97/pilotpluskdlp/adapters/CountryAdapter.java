package com.kairias97.pilotpluskdlp.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kairias97.pilotpluskdlp.R;
import com.kairias97.pilotpluskdlp.models.Country;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by kevin on 4/29/2017.
 */

public class CountryAdapter extends BaseAdapter {
    private List<Country> countries;
    private Context context;
    private int layout;

    public CountryAdapter(List<Country> countries, Context context, int layout) {
        this.countries = countries;
        this.context = context;
        this.layout = layout;
    }
    public int getCountryPos(Country c){
        return this.countries.indexOf(c);
    }
    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
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
        vh.itemName.setText(countries.get(position).getName());
        return convertView;
    }

    public class ViewHolder{
        TextView itemName;
        public ViewHolder(){
        }
    }
}
