package com.goncalossilva.googlemapseverywhere.model;

import com.goncalossilva.googlemapseverywhere.CameraUpdate;
import com.goncalossilva.googlemapseverywhere.GoogleMap;
import com.goncalossilva.googlemapseverywhere.GoogleMapOptions;
import com.goncalossilva.googlemapseverywhere.R;
import com.goncalossilva.googlemapseverywhere.util.LatLngUtils;

import android.content.Context;
import android.view.ViewConfiguration;

import java.util.ArrayList;
import java.util.List;

/* TODO: Make this invisible from the API. */
public final class GoogleMapBridge {
    private Context mContext;
    private JavaScriptBridge mJavaScriptBridge;

    private List<Object> mObjects = new ArrayList<>();

    private Integer mMyLocationMarkerIndex = null;

    public GoogleMapBridge(Context context, JavaScriptBridge javaScriptBridge, String elementId,
                           GoogleMapOptions options) {
        mContext = context;
        mJavaScriptBridge = javaScriptBridge;

        mJavaScriptBridge.evaluateJavascript(
                mContext.getString(
                        R.string.map_create_js,
                        elementId,
                        LatLngUtils.getJs(mContext, options.getCamera().target),
                        options.getCamera().zoom));
        mJavaScriptBridge.evaluateJavascript(
                mContext.getString(R.string.map_setup_js, ViewConfiguration.getLongPressTimeout()));
    }

    public void setMyLocationEnabled(boolean enabled) {
        if (mMyLocationMarkerIndex == null && enabled) {
            synchronized (this) {
                mMyLocationMarkerIndex = mObjects.size();
                mJavaScriptBridge.evaluateJavascript(
                        mContext.getString(R.string.show_my_location_enable_js, mMyLocationMarkerIndex));
                mObjects.add(null);
            }
        } else if (mMyLocationMarkerIndex != null && !enabled) {
            synchronized (this) {
                mJavaScriptBridge.evaluateJavascript(
                        mContext.getString(R.string.show_my_location_disable_js, mMyLocationMarkerIndex));
                mMyLocationMarkerIndex = null;
            }
        }
    }

    public void setOnMapLoadedCallback(GoogleMap.OnMapLoadedCallback callback) {
        mJavaScriptBridge.setOnMapLoadedCallback(callback);
    }

    public void setOnMapClickListener(GoogleMap.OnMapClickListener listener) {
        mJavaScriptBridge.setOnMapClickListener(listener);
    }

    public void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener listener) {
        mJavaScriptBridge.setOnMapLongClickListener(listener);
    }

    public void moveCamera(CameraUpdate update) {
        update.apply(mContext, mJavaScriptBridge);
    }

    public Marker addMarker(MarkerOptions options) {
        MarkerBridge markerBridge;
        synchronized (this) {
            markerBridge = new MarkerBridge(mContext, mJavaScriptBridge, mObjects.size(), options);
            markerBridge.add();
            mObjects.add(markerBridge);
        }
        return new Marker(markerBridge);
    }

    public Circle addCircle(CircleOptions options) {
        CircleBridge circleBridge;
        synchronized (this) {
            circleBridge = new CircleBridge(mContext, mJavaScriptBridge, mObjects.size(), options);
            circleBridge.add();
            mObjects.add(circleBridge);
        }
        return new Circle(circleBridge);
    }
}
