package com.goncalossilva.googlemapseverywhere.model;

public final class MarkerOptions {
    private LatLng mPosition;

    public MarkerOptions() {

    }

    public MarkerOptions position(LatLng position) {
        mPosition = position;
        return this;
    }

    public LatLng getPosition() {
        return mPosition;
    }
}
