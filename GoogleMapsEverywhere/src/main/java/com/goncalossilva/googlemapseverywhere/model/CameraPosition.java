package com.goncalossilva.googlemapseverywhere.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class CameraPosition implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.target, 0);
        dest.writeFloat(this.zoom);
    }

    private CameraPosition(Parcel in) {
        this.target = in.readParcelable(LatLng.class.getClassLoader());
        this.zoom = in.readFloat();
    }

    public static final Parcelable.Creator<CameraPosition> CREATOR = new Parcelable.Creator<CameraPosition>() {
        public CameraPosition createFromParcel(Parcel source) {
            return new CameraPosition(source);
        }

        public CameraPosition[] newArray(int size) {
            return new CameraPosition[size];
        }
    };
}
