package com.goncalossilva.googlemapseverywhere.model;

import com.goncalossilva.googlemapseverywhere.R;
import com.goncalossilva.googlemapseverywhere.util.ColorUtils;
import com.goncalossilva.googlemapseverywhere.util.LatLngUtils;

import android.content.Context;

class CircleBridge {
    private Context mContext;
    private JavaScriptBridge mJavaScriptBridge;

    private int mIndex;

    private CircleOptions mOptions;

    public CircleBridge(Context context, JavaScriptBridge javaScriptBridge, int index, CircleOptions options) {
        mContext = context;
        mJavaScriptBridge = javaScriptBridge;
        mIndex = index;
        mOptions = options;
    }

    public void add() {
        mJavaScriptBridge.evaluateJavascript(
                mContext.getString(
                        R.string.circle_create_js,
                        mIndex,
                        LatLngUtils.getJs(mContext, mOptions.getCenter()),
                        mOptions.getRadius(),
                        ColorUtils.getCss(mContext, mOptions.getFillColor()),
                        mOptions.getStrokeWidth(),
                        ColorUtils.getCss(mContext, mOptions.getStrokeColor())));
    }

    public void remove() {
        mJavaScriptBridge.evaluateJavascript(mContext.getString(R.string.circle_remove_js, mIndex));
    }

    public LatLng getCenter() {
        return mOptions.getCenter();
    }

    public double getRadius() {
        return mOptions.getRadius();
    }

    public int getFillColor() {
        return mOptions.getFillColor();
    }

    public float getStrokeWidth() {
        return mOptions.getStrokeWidth();
    }

    public int getStrokeColor() {
        return mOptions.getStrokeColor();
    }

    public void setCenter(LatLng center) {
        update("center", LatLngUtils.getJs(mContext, center), false);
        mOptions.center(center);
    }

    public void setRadius(double radius) {
        update("radius", radius, false);
        mOptions.radius(radius);
    }

    public void setFillColor(int fillColor) {
        update("fillColor", ColorUtils.getCss(mContext, fillColor), true);
        mOptions.fillColor(fillColor);
    }

    public void setStrokeWidth(float strokeWidth) {
        update("strokeWeight", strokeWidth, false);
        mOptions.strokeWidth(strokeWidth);
    }

    public void setStrokeColor(int strokeColor) {
        update("strokeColor", ColorUtils.getCss(mContext, strokeColor), true);
        mOptions.strokeColor(strokeColor);
    }

    private void update(String fieldName, Object value, boolean addQuotes) {
        mJavaScriptBridge.evaluateJavascript(
                mContext.getString(
                        R.string.mvcobject_update_js, mIndex, fieldName, addQuotes ? "\"" + value + "\"" : value));
    }
}
