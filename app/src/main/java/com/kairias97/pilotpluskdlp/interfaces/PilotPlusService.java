package com.kairias97.pilotpluskdlp.interfaces;

import com.kairias97.pilotpluskdlp.models.Country;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kevin on 4/29/2017.
 */

public interface PilotPlusService {
    @GET("/countries/airports")
    Call<CountriesResponse> getAllInfo();
    public class CountriesResponse{
        RealmList<Country> countries;

        public CountriesResponse() {

        }

        public CountriesResponse(RealmList<Country> countries) {
            this.countries = countries;
        }

        public RealmList<Country> getCountries() {
            return countries;
        }

        public void setCountries(RealmList<Country> countries) {
            this.countries = countries;
        }
    }
}
