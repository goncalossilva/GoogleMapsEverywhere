package com.goncalossilva.googlemapseverywhere;

import com.goncalossilva.googlemapseverywhere.model.Circle;
import com.goncalossilva.googlemapseverywhere.model.CircleOptions;
import com.goncalossilva.googlemapseverywhere.model.GoogleMapBridge;
import com.goncalossilva.googlemapseverywhere.model.JavaScriptBridge;
import com.goncalossilva.googlemapseverywhere.model.LatLng;
import com.goncalossilva.googlemapseverywhere.model.Marker;
import com.goncalossilva.googlemapseverywhere.model.MarkerOptions;

import android.content.Context;

public final class GoogleMap {
    private Context mContext;

    private GoogleMapBridge mGoogleMapBridge;

    GoogleMap(Context context, JavaScriptBridge javaScriptBridge, String mapId, GoogleMapOptions options) {
        mGoogleMapBridge = new GoogleMapBridge(context, javaScriptBridge, mapId, options);
    }

    public void setMyLocationEnabled(boolean enabled) {
        mGoogleMapBridge.setMyLocationEnabled(enabled);
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback callback) {
        mGoogleMapBridge.setOnMapLoadedCallback(callback);
    }

    public void setOnMapClickListener(OnMapClickListener listener) {
        mGoogleMapBridge.setOnMapClickListener(listener);
    }

    public void setOnMapLongClickListener(OnMapLongClickListener listener) {
        mGoogleMapBridge.setOnMapLongClickListener(listener);
    }

    public void moveCamera(CameraUpdate update) {
        mGoogleMapBridge.moveCamera(update);
    }

    public Marker addMarker(MarkerOptions options) {
        return mGoogleMapBridge.addMarker(options);
    }

    public Circle addCircle(CircleOptions options) {
        return mGoogleMapBridge.addCircle(options);
    }

    public interface OnMapLoadedCallback {
        public void onMapLoaded();
    }

    public interface OnMapClickListener {
        public void onMapClick(LatLng point);
    }

    public interface OnMapLongClickListener {
        public void onMapLongClick(LatLng point);
    }
}
