package com.kairias97.pilotpluskdlp.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kevin on 4/30/2017.
 */

public class Trip extends RealmObject {
    @PrimaryKey
    String id;
    String trip_name;
    Airport originAirport;
    Airport destinationAirport;
    Country originCountry;
    Country destinationCountry;
    String createdAt;

    public Trip(){

    }

    public void setId(String id) {
        this.id = id;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(Country destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Trip(UUID id, String trip_name, Airport originAirport, Airport destinationAirport, Country origin, Country destination, String created) {
        this.id = id.toString();
        this.trip_name = trip_name;
        this.originCountry = origin;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.destinationCountry = destination;
        this.createdAt =created;
    }

    public String getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id.toString();
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
}
