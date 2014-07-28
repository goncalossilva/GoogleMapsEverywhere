package com.goncalossilva.googlemapseverywhere.model;

public class LatLngBounds {
    public final LatLng northeast;
    public final LatLng southwest;

    public LatLngBounds(LatLng northeast, LatLng southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }
}
