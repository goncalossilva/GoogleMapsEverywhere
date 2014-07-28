package com.goncalossilva.googlemapseverywhere;

import com.goncalossilva.googlemapseverywhere.model.LatLng;
import com.goncalossilva.googlemapseverywhere.model.LatLngBounds;

public final class CameraUpdateFactory {
    public static CameraUpdate newLatLngZoom(LatLng latLng, float zoom) {
        return new CameraUpdate.LatLngZoom(latLng, zoom).build();
    }

    /* TODO: Support the padding parameter. */
    public static CameraUpdate newLatLngBounds(LatLngBounds bounds) {
        return new CameraUpdate.LatLngBounds(bounds).build();
    }
}
