package com.goncalossilva.googlemapseverywhere.util;

import com.goncalossilva.googlemapseverywhere.R;
import com.goncalossilva.googlemapseverywhere.model.LatLngBounds;

import android.content.Context;

public class LatLngBoundsUtils {
    public static String getJs(Context context, LatLngBounds latLngBounds) {
        return context.getString(
                R.string.lat_lng_bounds_init_js,
                LatLngUtils.getJs(context, latLngBounds.southwest),
                LatLngUtils.getJs(context, latLngBounds.northeast));
    }
}
