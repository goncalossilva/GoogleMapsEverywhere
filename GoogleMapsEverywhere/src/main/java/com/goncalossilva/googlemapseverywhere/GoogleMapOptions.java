package com.goncalossilva.googlemapseverywhere;

import com.goncalossilva.googlemapseverywhere.model.CameraPosition;

public final class GoogleMapOptions {
    private CameraPosition mCameraPosition;

    public GoogleMapOptions() {

    }

    public GoogleMapOptions camera(CameraPosition camera) {
        mCameraPosition = camera;
        return this;
    }

    public CameraPosition getCamera() {
        return mCameraPosition;
    }
}
