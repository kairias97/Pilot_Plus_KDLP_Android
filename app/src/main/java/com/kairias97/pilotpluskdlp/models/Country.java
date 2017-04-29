package com.kairias97.pilotpluskdlp.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kevin on 4/29/2017.
 */

public class Country extends RealmObject {
    @PrimaryKey
    String code;
    String name;
    RealmList<Airport> airports;

    public Country(String code, String name, RealmList<Airport> airports) {
        this.code = code;
        this.name = name;
        this.airports = airports;
    }

    public Country() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Airport> getAirports() {
        return airports;
    }

    public void setAirports(RealmList<Airport> airports) {
        this.airports = airports;
    }
}
