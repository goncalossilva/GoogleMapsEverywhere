package com.goncalossilva.googlemapseverywhere.model;

import com.goncalossilva.googlemapseverywhere.R;
import com.goncalossilva.googlemapseverywhere.util.LatLngUtils;

import android.content.Context;

class MarkerBridge {
    private Context mContext;
    private JavaScriptBridge mJavaScriptBridge;

    private int mIndex;

    private MarkerOptions mOptions;

    public MarkerBridge(Context context, JavaScriptBridge javaScriptBridge, int index, MarkerOptions options) {
        mContext = context;
        mJavaScriptBridge = javaScriptBridge;
        mIndex = index;
        mOptions = options;
    }

    public void add() {
        mJavaScriptBridge.evaluateJavascript(
                mContext.getString(
                        R.string.marker_create_js, mIndex,
                        LatLngUtils.getJs(mContext, mOptions.getPosition())));
    }

    public void remove() {
        mJavaScriptBridge.evaluateJavascript(mContext.getString(R.string.marker_remove_js, mIndex));
    }

    public LatLng getPosition() {
        return mOptions.getPosition();
    }

    public void setPosition(LatLng position) {
        update("position", LatLngUtils.getJs(mContext, position));
        mOptions.position(position);
    }

    private void update(String fieldName, Object value) {
        mJavaScriptBridge.evaluateJavascript(mContext.getString(R.string.mvcobject_update_js, mIndex, fieldName, value));
    }
}
