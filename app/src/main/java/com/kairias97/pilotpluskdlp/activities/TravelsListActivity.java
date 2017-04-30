package com.kairias97.pilotpluskdlp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kairias97.pilotpluskdlp.FormAddActivity;
import com.kairias97.pilotpluskdlp.R;
import com.kairias97.pilotpluskdlp.adapters.TripAdapter;
import com.kairias97.pilotpluskdlp.models.Trip;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class TravelsListActivity extends AppCompatActivity {
    @BindView(R.id.travels_list)
    ListView travelList;
    private TripAdapter tripAdapter;
    private RealmResults<Trip> trips;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travels_summary);
        this.setTitle("Trips History");
        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_travel);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelsListActivity.this, FormAddActivity.class);
                startActivity(intent);
            }
        });
        realm = Realm.getDefaultInstance();
        trips = realm.where(Trip.class).findAll();

        tripAdapter = new TripAdapter(trips, this, R.layout.trip_item);
        travelList.setAdapter(tripAdapter);
        travelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TravelsListActivity.this, FormAddActivity.class);
                intent.putExtra("id", trips.get(position).getId());
                startActivity(intent);
            }
        });
        trips.addChangeListener(new RealmChangeListener<RealmResults<Trip>>() {
            @Override
            public void onChange(RealmResults<Trip> element) {
                tripAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.see_poi:
                Intent intent = new Intent(this, PoiMap.class);
                startActivity(intent);
                return true;
            case R.id.add_poi:
                Intent intent2 = new Intent(this, AddPoiActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
