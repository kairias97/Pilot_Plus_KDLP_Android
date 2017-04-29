package com.kairias97.pilotpluskdlp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.Menu;

import com.kairias97.pilotpluskdlp.activities.TravelsListActivity;
import com.kairias97.pilotpluskdlp.interfaces.PilotPlusService;
import com.kairias97.pilotpluskdlp.interfaces.PilotPlusService.CountriesResponse;
import com.kairias97.pilotpluskdlp.models.Country;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private RealmResults<Country> countryList;
    private PilotPlusService airportsService;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        realm = Realm.getDefaultInstance();
        countryList = realm.where(Country.class).findAll();
        if(countryList.size() > 0){
            moveToTravelList(SPLASH_DISPLAY_LENGTH);
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://pilot-plus-uam.herokuapp.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            airportsService = retrofit.create(PilotPlusService.class);
            Call<CountriesResponse> airportsCall = airportsService.getAllInfo();
            airportsCall.enqueue(new Callback<CountriesResponse>() {
                @Override
                public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                    if(response.isSuccessful()){
                        CountriesResponse airportResponse = response.body();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(airportResponse.getCountries());
                        realm.commitTransaction();
                        moveToTravelList(100);
                    }
                }

                @Override
                public void onFailure(Call<CountriesResponse> call, Throwable t) {
                    moveToTravelList(SPLASH_DISPLAY_LENGTH);
                }
            });
        }

    }
    private void moveToTravelList(int length){
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, TravelsListActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, length );
    }
}
