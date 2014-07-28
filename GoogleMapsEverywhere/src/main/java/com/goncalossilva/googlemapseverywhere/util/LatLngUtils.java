package com.goncalossilva.googlemapseverywhere.util;

import com.goncalossilva.googlemapseverywhere.R;
import com.goncalossilva.googlemapseverywhere.model.LatLng;

import android.content.Context;

public class LatLngUtils {
    public static String getJs(Context context, LatLng latLng) {
        return context.getString(R.string.lat_lng_init_js, latLng.latitude, latLng.longitude);
    }
}
