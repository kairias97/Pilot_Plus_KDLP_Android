package com.kairias97.pilotpluskdlp;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kairias97.pilotpluskdlp.adapters.AirportAdapter;
import com.kairias97.pilotpluskdlp.adapters.CountryAdapter;
import com.kairias97.pilotpluskdlp.models.Airport;
import com.kairias97.pilotpluskdlp.models.Country;
import com.kairias97.pilotpluskdlp.models.Trip;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class FormAddActivity extends AppCompatActivity implements OnMapReadyCallback {
    private CountryAdapter countryOrigAdapter;
    private CountryAdapter countryDestAdapter;
    private AirportAdapter airportOrigAdapter;
    private AirportAdapter airportDestAdapter;
    private GoogleMap map;
    RealmResults<Country> originCountries;
    RealmResults<Country> destinationCountries;
    Realm realm;
    @BindView(R.id.edit_txt_trip_name)
    EditText editTextTripName;
    @BindView(R.id.CountryA)
    Spinner spinnerCountryA;
    @BindView(R.id.CountryB)
    Spinner spinnerCountryB;
    @BindView(R.id.AirportA)
    Spinner spinnerAirportA;
    @BindView(R.id.AirportB)
    Spinner spinnerAirportB;
    @BindView(R.id.ButtonSubmit)
    Button btnSubmit;
    @OnClick(R.id.ButtonSubmit)
    void submit(){
        Country originC = (Country)spinnerCountryA.getSelectedItem();
        Country destinationC = (Country)spinnerCountryB.getSelectedItem();
        Airport originA = (Airport) spinnerAirportA.getSelectedItem();
        Airport destinationA = (Airport) spinnerAirportB.getSelectedItem();
        if (!editTextTripName.getText().toString().equals("") && !originC.getCode().equals("-1") &&
                !destinationC.getCode().equals("-2") && originA.getId() != -1 && destinationA.getId() != -2){
            Calendar c = Calendar.getInstance();
            String tripName = editTextTripName.getText().toString();
            String date = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) +1)
                    + "/" + Integer.toString(c.get(Calendar.DAY_OF_MONTH));
            Trip myTrip = new Trip(UUID.randomUUID(), tripName, originA, destinationA, originC, destinationC, date);
            realm.beginTransaction();
            realm.copyToRealm(myTrip);
            realm.commitTransaction();
            setMapPoints(originA, destinationA);
            disableInputs();
            Toast.makeText(this, "Trip registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

    }

    private void disableInputs() {
        btnSubmit.setEnabled(false);
        editTextTripName.setEnabled(false);
        spinnerAirportA.setEnabled(false);
        spinnerAirportB.setEnabled(false);
        spinnerCountryA.setEnabled(false);
        spinnerCountryB.setEnabled(false);
        spinnerAirportB.setEnabled(false);
    }

    private boolean isNew;
    private String tripId;
    private void setMapPoints(Airport originA, Airport destinationA) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(originA.getLatitude_deg(), originA.getLongitude_deg()))
                .title("Departure " + originA.getName()));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(destinationA.getLatitude_deg(), destinationA.getLongitude_deg()))
                .title("Arrival " + destinationA.getName()));
        // Polylines are useful for marking paths and routes on the map.
        map.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(originA.getLatitude_deg(), originA.getLongitude_deg()))
                .add(new LatLng(destinationA.getLatitude_deg(), destinationA.getLongitude_deg()))
                .color(Color.DKGRAY));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        originCountries = realm.where(Country.class).notEqualTo("code", "-2").findAllSorted("name");
        destinationCountries = realm.where(Country.class).notEqualTo("code", "-1").findAllSorted("name");
        countryOrigAdapter = new CountryAdapter(originCountries, this, R.layout.spinner_text_view);
        countryDestAdapter = new CountryAdapter(destinationCountries, this, R.layout.spinner_text_view);
        //Aca continuar con los adapters.. zzzzz
        airportOrigAdapter = new AirportAdapter(originCountries.get(0).getAirports(), this, R.layout.spinner_text_view);
        airportDestAdapter = new AirportAdapter(destinationCountries.get(0).getAirports(), this, R.layout.spinner_text_view);
        spinnerAirportA.setAdapter(airportOrigAdapter);
        spinnerAirportB.setAdapter(airportDestAdapter);
        spinnerCountryA.setAdapter(countryOrigAdapter);
        spinnerCountryB.setAdapter(countryDestAdapter);
        spinnerCountryA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                airportOrigAdapter.setAirports(originCountries.get(position).getAirports());
                airportOrigAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCountryB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                airportDestAdapter.setAirports(destinationCountries.get(position).getAirports());
                airportDestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Country origC = realm.where(Country.class).equalTo("code", "-1").findFirst();
        spinnerCountryA.setSelection(countryOrigAdapter.getCountryPos(origC));
        Country destC = realm.where(Country.class).equalTo("code", "-2").findFirst();
        spinnerCountryB.setSelection(countryDestAdapter.getCountryPos(destC));
        ///Hacer bind a los spinners....
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.travel_map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(getIntent().getExtras() != null){
            isNew = false;
            tripId = getIntent().getExtras().getString("id");
            Trip actualTrip = realm.where(Trip.class).equalTo("id", tripId).findFirst();
            editTextTripName.setText(actualTrip.getTrip_name());
            spinnerCountryA.setSelection(countryOrigAdapter.getCountryPos(actualTrip.getOriginCountry()));
            spinnerCountryB.setSelection(countryDestAdapter.getCountryPos(actualTrip.getDestinationCountry()));
            spinnerAirportA.setSelection(airportOrigAdapter.getCountryPos(actualTrip.getOriginAirport()));
            spinnerAirportB.setSelection(airportDestAdapter.getCountryPos(actualTrip.getDestinationAirport()));
            disableInputs();
            setMapPoints(actualTrip.getOriginAirport(), actualTrip.getDestinationAirport());
        }else {
            isNew = true;
        }
        /*googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));*/
    }
    /*
    public class MapsMarkerActivity extends AppCompatActivity
            implements OnMapReadyCallback {
        // Include the OnCreate() method here too, as described above.

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_form_add);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            // Add a marker in Sydney, Australia,
            // and move the map's camera to the same location.
            LatLng sydney = new LatLng(-33.852, 151.211);
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
    */
}


