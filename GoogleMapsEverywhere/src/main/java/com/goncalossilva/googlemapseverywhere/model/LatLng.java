package com.goncalossilva.googlemapseverywhere.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class LatLng implements Parcelable {
    public final double latitude;
    public final double longitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    private LatLng(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() {
        public LatLng createFromParcel(Parcel source) {
            return new LatLng(source);
        }

        public LatLng[] newArray(int size) {
            return new LatLng[size];
        }
    };
}
