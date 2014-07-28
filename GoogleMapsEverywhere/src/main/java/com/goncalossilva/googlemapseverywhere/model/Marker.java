package com.goncalossilva.googlemapseverywhere.model;

public final class Marker {
    private MarkerBridge mMarkerBridge;

    Marker(MarkerBridge markerBridge) {
        mMarkerBridge = markerBridge;
    }

    public LatLng getPosition() {
        return mMarkerBridge.getPosition();
    }

    public void setPosition(LatLng position) {
        mMarkerBridge.setPosition(position);
    }
}
