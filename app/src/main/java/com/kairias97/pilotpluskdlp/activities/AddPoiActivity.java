package com.kairias97.pilotpluskdlp.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kairias97.pilotpluskdlp.R;
import com.kairias97.pilotpluskdlp.interfaces.PilotPlusService;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPoiActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener{
    private GoogleMap map;
    private PilotPlusService airportsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poi);
        this.setTitle("World Map");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pilot-plus-uam.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        airportsService = retrofit.create(PilotPlusService.class);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.poi_add_map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.clear();
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latLng.latitude, latLng.longitude))
                .title("Selected point"));
        showDialog(latLng);
    }
    private void showDialog(final LatLng latLng){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(AddPoiActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle("Add new POI").setView(R.layout.activity_add_poi)
                .setView(R.layout.add_poi_dialog);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText title;
                EditText description;
                title = ButterKnife.findById((Dialog) dialog, R.id.poi_title);
                description = ButterKnife.findById((Dialog) dialog, R.id.poi_description);
                PilotPlusService.bodyPoi body = new PilotPlusService.bodyPoi(title.getText().toString(),
                        description.getText().toString(), latLng.latitude, latLng.longitude );
                Call<PilotPlusService.AddedPoi> poiSaveCall = airportsService.addPoi(body);
                poiSaveCall.enqueue(new Callback<PilotPlusService.AddedPoi>() {
                    @Override
                    public void onResponse(Call<PilotPlusService.AddedPoi> call, Response<PilotPlusService.AddedPoi> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(AddPoiActivity.this, "POI added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PilotPlusService.AddedPoi> call, Throwable t) {

                    }
                });

            }
        });


// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
