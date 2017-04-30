package com.kairias97.pilotpluskdlp.interfaces;

import com.google.gson.annotations.SerializedName;
import com.kairias97.pilotpluskdlp.models.Country;
import com.kairias97.pilotpluskdlp.models.Poi;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kevin on 4/29/2017.
 */

public interface PilotPlusService {
    @GET("countries/airports")
    Call<CountriesResponse> getAllInfo();
    @GET("poi")
    Call<PoiResponse> getAllPoi();
    @POST("poi")
    Call<AddedPoi> addPoi(@Body bodyPoi body);
    public class bodyPoi{
        @SerializedName("title")
        String title;
        @SerializedName("description")
        String description;
        @SerializedName("latitude")
        Double latitude;
        @SerializedName("longitude")
        Double longitude;
        public bodyPoi(){

        }

        public bodyPoi(String title, String description, Double latitude, Double longitude) {
            this.title = title;
            this.description = description;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
    public class AddedPoi{
        @SerializedName("id")
        int id;
        @SerializedName("title")
        String title;
        @SerializedName("description")
        String description;
        @SerializedName("latitude")
        Double latitude;
        @SerializedName("longitude")
        Double longitude;
        public AddedPoi(){

        }

        public AddedPoi(int id, String title, String description, Double latitude, Double longitude) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
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

    public class PoiResponse{
        RealmList<Poi> poi;

        public PoiResponse(RealmList<Poi> poi) {
            this.poi = poi;
        }

        public RealmList<Poi> getPoi() {
            return poi;
        }

        public void setPoi(RealmList<Poi> poi) {
            this.poi = poi;
        }
    }
}
