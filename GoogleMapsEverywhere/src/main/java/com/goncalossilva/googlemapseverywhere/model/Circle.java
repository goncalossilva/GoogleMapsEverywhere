package com.goncalossilva.googlemapseverywhere.model;

public final class Circle {
    private CircleBridge mCircleBridge;

    Circle(CircleBridge circleBridge) {
        mCircleBridge = circleBridge;
    }

    public LatLng getCenter() {
        return mCircleBridge.getCenter();
    }

    public double getRadius() {
        return mCircleBridge.getRadius();
    }

    public int getFillColor() {
        return mCircleBridge.getFillColor();
    }

    public float getStrokeWidth() {
        return mCircleBridge.getStrokeWidth();
    }

    public int getStrokeColor() {
        return mCircleBridge.getStrokeColor();
    }

    public void setCenter(LatLng center) {
        mCircleBridge.setCenter(center);
    }

    public void setRadius(double radius) {
        mCircleBridge.setRadius(radius);
    }

    public void setFillColor(int fillColor) {
        mCircleBridge.setFillColor(fillColor);
    }

    public void setStrokeWidth(float strokeWidth) {
        mCircleBridge.setStrokeWidth(strokeWidth);
    }

    public void setStrokeColor(int strokeColor) {
        mCircleBridge.setStrokeColor(strokeColor);
    }
}
