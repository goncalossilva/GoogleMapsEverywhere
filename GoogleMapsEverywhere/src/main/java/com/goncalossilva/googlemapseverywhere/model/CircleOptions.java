package com.goncalossilva.googlemapseverywhere.model;

import android.graphics.Color;

public final class CircleOptions {
    private LatLng mCenter = null;
    private double mRadius = 0f;
    private int mFillColor = Color.TRANSPARENT;
    private float mStrokeWidth = 10f;
    private int mStrokeColor = Color.BLACK;

    public CircleOptions() {

    }

    public CircleOptions center(LatLng center) {
        mCenter = center;
        return this;
    }

    public CircleOptions radius(double radius) {
        mRadius = radius;
        return this;
    }

    public CircleOptions fillColor(int fillColor) {
        mFillColor = fillColor;
        return this;
    }

    public CircleOptions strokeWidth(float strokeWidth) {
        mStrokeWidth = strokeWidth;
        return this;
    }

    public CircleOptions strokeColor(int strokeColor) {
        mStrokeColor = strokeColor;
        return this;
    }

    public LatLng getCenter() {
        return mCenter;
    }

    public double getRadius() {
        return mRadius;
    }

    public int getFillColor() {
        return mFillColor;
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public int getStrokeColor() {
        return mStrokeColor;
    }
}
