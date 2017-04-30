package com.kairias97.pilotpluskdlp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kairias97.pilotpluskdlp.R;
import com.kairias97.pilotpluskdlp.interfaces.PilotPlusService;
import com.kairias97.pilotpluskdlp.models.Poi;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoiMap extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap map;
    private PilotPlusService airportsService;
    Realm realm;
    RealmResults<Poi> poiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_map);
        this.setTitle("POI Map");
        realm = Realm.getDefaultInstance();
        poiList = realm.where(Poi.class).findAll();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pilot-plus-uam.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        airportsService = retrofit.create(PilotPlusService.class);
        Call<PilotPlusService.PoiResponse> poiGetCall = airportsService.getAllPoi();
        poiGetCall.enqueue(new Callback<PilotPlusService.PoiResponse>() {
            @Override
            public void onResponse(Call<PilotPlusService.PoiResponse> call, Response<PilotPlusService.PoiResponse> response) {
                if(response.isSuccessful()){
                    PilotPlusService.PoiResponse pois = response.body();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(pois.getPoi());
                    realm.commitTransaction();
                }
                initMap();
            }

            @Override
            public void onFailure(Call<PilotPlusService.PoiResponse> call, Throwable t) {
                initMap();
            }
        });

    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.poi_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        for(Poi poi: poiList){
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(poi.getLatitude(), poi.getLongitude()))
                    .title(poi.getTitle() + " - " + poi.getDescription()));
        }
    }
}
