package com.kairias97.pilotpluskdlp.models;

import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by kevin on 4/29/2017.
 */

public class Airport extends RealmObject {
    @PrimaryKey
    int id;
    String iata_code;
    String name;
    String iso_country;
    double latitude_deg;
    double longitude_deg;

    public Airport(int id, String iata_code, String name, String iso_country, double latitude_deg, double longitude_deg) {
        this.id = id;
        this.iata_code = iata_code;
        this.name = name;
        this.iso_country = iso_country;
        this.latitude_deg = latitude_deg;
        this.longitude_deg = longitude_deg;
    }

    public Airport() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIata_code() {
        return iata_code;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_country() {
        return iso_country;
    }

    public void setIso_country(String iso_country) {
        this.iso_country = iso_country;
    }

    public double getLatitude_deg() {
        return latitude_deg;
    }

    public void setLatitude_deg(double latitude_deg) {
        this.latitude_deg = latitude_deg;
    }

    public double getLongitude_deg() {
        return longitude_deg;
    }

    public void setLongitude_deg(double longitude_deg) {
        this.longitude_deg = longitude_deg;
    }
}

