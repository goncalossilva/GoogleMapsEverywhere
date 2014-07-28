package com.goncalossilva.googlemapseverywhere.model;

public final class CameraPosition {
    public final LatLng target;
    public final float zoom;

    private CameraPosition(LatLng target, float zoom) {
        this.target = target;
        this.zoom = zoom;
    }

    public static final class Builder {
        private LatLng mTarget;
        private float mZoom;

        public Builder target(LatLng target) {
            mTarget = target;
            return this;
        }

        public Builder zoom(float zoom) {
            mZoom = zoom;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(mTarget, mZoom);
        }
    }
}
