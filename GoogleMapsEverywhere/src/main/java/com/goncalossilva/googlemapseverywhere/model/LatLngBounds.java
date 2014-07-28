package com.goncalossilva.googlemapseverywhere.model;

public class LatLngBounds {
    public final LatLng northeast;
    public final LatLng southwest;

    public LatLngBounds(LatLng southwest, LatLng northeast) {
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public LatLng getCenter() {
        double latitudeCenter = (southwest.latitude + northeast.latitude) / 2D;
        double longitudeCenter;
        if (southwest.longitude <= northeast.longitude) {
            longitudeCenter = (northeast.longitude + southwest.longitude) / 2D;
        }
        else {
            longitudeCenter = (northeast.longitude + 360D + southwest.longitude) / 2D;
        }
        return new LatLng(latitudeCenter, longitudeCenter);
    }
}
